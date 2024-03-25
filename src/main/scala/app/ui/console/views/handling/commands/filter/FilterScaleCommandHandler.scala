package app.ui.console.views.handling.commands.filter

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler

import scala.util.Try

class FilterScaleCommandHandler(consoleController: ConsoleController) extends CommandHandler {

  override def commandName: String = "--scale"
  override def argsCount: Int = 1

  override protected def processCommand(args: Seq[String]): Unit = {
    val scaleString = args.head
    if (Try(scaleString.toDouble).isFailure) throw new IllegalArgumentException(s"Value '$scaleString' is not a double (double type is required for --scale)")
    val scale: Double = scaleString.toDouble

    consoleController.addFilterScaleJob(scale)
  }

}
