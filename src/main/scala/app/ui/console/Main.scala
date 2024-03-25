package app.ui.console

import app.business.pipeline.SimplePipeline
import app.ui.console.controllers.ConsoleController
import app.ui.console.views.ConsoleView
import exporters.text.stream.StdOutputExporter

object Main extends App {

  private val consoleExporter = new StdOutputExporter
  private val controller = new ConsoleController(consoleExporter, new SimplePipeline)
  private val view = ConsoleView(controller)

  view.run(args.toSeq)

}
