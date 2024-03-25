package app.ui.console.views.handling.commands.`export`

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class OutputFileCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--output-file"
  override def argsCount: Int = 1

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.addOutputFileJob(args.head)
  }

}
