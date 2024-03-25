package app.ui.console.views.handling.commands.filter

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

class FilterInvertCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--invert"
  override def argsCount: Int = 0

  override protected def processCommand(args: Seq[String]): Unit = {
    consoleController.addFilterInvertJob()
  }

}
