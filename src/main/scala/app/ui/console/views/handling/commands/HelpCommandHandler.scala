package app.ui.console.views.handling.commands

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class HelpCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--help"
  override def argsCount: Int = 0

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.showHelp()
  }

}
