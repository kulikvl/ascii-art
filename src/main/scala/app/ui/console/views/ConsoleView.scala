package app.ui.console.views

import app.business.components.converters.cli.ArgsToCmds
import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.`export`.{OutputConsoleCommandHandler, OutputFileCommandHandler}
import app.ui.console.views.handling.commands.convert.{CustomTableConvertCommandHandler, PredefinedTableConvertCommandHandler}
import app.ui.console.views.handling.commands.filter.{FilterInvertCommandHandler, FilterRotateCommandHandler, FilterScaleCommandHandler}
import app.ui.console.views.handling.commands.{HelpCommandHandler, IndexCommandHandler}
import app.ui.console.views.handling.commands.load.{LoadImageCommandHandler, LoadRandomImageCommandHandler}
import app.ui.console.views.handling.errors.NoCommandMatchedHandler
import responsibilityChain.Handler

class ConsoleView(consoleController: ConsoleController, commandHandler: CommandHandler) {

  /**
   * Starts the application.
   */
  def run(args: Seq[String]): Unit = {
    var parsedArgs = new ArgsToCmds().convert(args)

    // If no cli-argument is specified, then return index page
    if (parsedArgs.isEmpty)
      parsedArgs = parsedArgs.appended(Seq("--index"))

    // Resolve all the parsed args (commands)
    try {
      parsedArgs.foreach(cmd => Handler.resolveAll(commandHandler, cmd))
    } catch {
      case e: IllegalArgumentException =>
        consoleController.showError(s"Error while resolving commands:\n    ${e.getMessage}")
        return
    }

    // Run the pipeline (main execution unit of the application)
    try {
      consoleController.runPipeline()
    } catch {
      case e: RuntimeException =>
        consoleController.showError(e.getMessage)
    }

  }

}

object ConsoleView {

  /**
   * Creates ConsoleView with initial commands.
   */
  def apply(consoleController: ConsoleController): ConsoleView = {
    val handlers = commandHandlers(consoleController)
    new ConsoleView(consoleController, handlers)
  }

  /**
   * Creates common command handlers for the view.
   */
  def commandHandlers(consoleController: ConsoleController): CommandHandler = {
    val indexHandler = new IndexCommandHandler(consoleController)
    val helpHandler = new HelpCommandHandler(consoleController)
    val loadImageHandler = new LoadImageCommandHandler(consoleController)
    val loadRandomImageHandler = new LoadRandomImageCommandHandler(consoleController)
    val conversionTableCommandHandler = new PredefinedTableConvertCommandHandler(consoleController)
    val conversionCustomTableCommandHandler = new CustomTableConvertCommandHandler(consoleController)
    val filterInvertCommandHandler = new FilterInvertCommandHandler(consoleController)
    val filterRotateCommandHandler = new FilterRotateCommandHandler(consoleController)
    val filterScaleCommandHandler = new FilterScaleCommandHandler(consoleController)
    val outputConsoleCommandHandler = new OutputConsoleCommandHandler(consoleController)
    val outputFileCommandHandler = new OutputFileCommandHandler(consoleController)

    val initialHandler: CommandHandler = indexHandler

    initialHandler
      .setNext(helpHandler)
      .setNext(loadImageHandler)
      .setNext(loadRandomImageHandler)
      .setNext(conversionTableCommandHandler)
      .setNext(conversionCustomTableCommandHandler)
      .setNext(filterInvertCommandHandler)
      .setNext(filterRotateCommandHandler)
      .setNext(filterScaleCommandHandler)
      .setNext(outputConsoleCommandHandler)
      .setNext(outputFileCommandHandler)
      .setNext(new NoCommandMatchedHandler)

    initialHandler
  }

}
