package app.ui.console.views.handling.commands.convert

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class PredefinedTableConvertCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--table"
  override def argsCount: Int = 1

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.addPredefinedTableConvertJob(args.head)
  }

}
