package app.ui.console.views.handling.commands.filter

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

import scala.util.Try

class FilterRotateCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--rotate"
  override def argsCount: Int = 1

  override protected def processCommand(args: Seq[String]): Unit = {
    val angleString = args.head
    if (Try(angleString.toInt).isFailure) throw new IllegalArgumentException(s"Value '$angleString' is not an integer (integer type is required for --rotate)")
    val angle: Int = angleString.toInt

    consoleController.addFilterRotateJob(angle)
  }

}
