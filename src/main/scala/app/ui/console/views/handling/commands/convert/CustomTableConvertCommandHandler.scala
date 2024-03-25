package app.ui.console.views.handling.commands.convert

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class CustomTableConvertCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--custom-table"
  override def argsCount: Int = 1

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.addCustomTableConvertJob(args.head)
  }

}
