package app.ui.console.views.handling.commands.load

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class LoadImageCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--image"
  override def argsCount: Int = 1

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.addLoadImageJob(args.head)
  }

}
