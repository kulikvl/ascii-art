package app.ui.console.views.handling.commands.load

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class LoadRandomImageCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--image-random"
  override def argsCount: Int = 0

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.addLoadRandomImageJob()
  }

}
