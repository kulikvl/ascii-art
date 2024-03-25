package app.ui.console.views.handling.commands.`export`

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class OutputConsoleCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--output-console"
  override def argsCount: Int = 0

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.addOutputConsoleJob()
  }

}
