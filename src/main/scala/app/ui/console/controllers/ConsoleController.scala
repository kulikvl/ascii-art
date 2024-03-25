package app.ui.console.controllers

import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.`export`.specific.{OutputConsoleJob, OutputFileJob}
import app.business.pipeline.jobs.convert.specific.{CustomTableConvertJob, PredefinedTableConvertJob}
import app.business.pipeline.jobs.filter.specific.{FilterInvertJob, FilterRotateJob, FilterScaleJob}
import app.business.pipeline.jobs.load.specific.{LoadImageJob, LoadRandomImageJob}
import app.ui.console.views.pages.text.TextPage
import app.ui.console.views.pages.text.specific.{ErrorPage, HelpPage, IndexPage}
import exporters.text.TextExporter

/**
 * Controller for the console.
 *
 * Note: there is no point in creating a generic controller for this app (moreover, the classic MVC pattern is not very applicable to this app),
 * as different controller implementations would have significantly different method prescriptions
 * (for example, a WebController in a typical Spring application would always return the name of the HTML page, ...).
 */
class ConsoleController(textExporter: TextExporter, pipeline: Pipeline) {

  // Commands for displaying pages (outside the pipeline)
  def showHelp(): Unit = render(new HelpPage)
  def showIndex(): Unit = render(new IndexPage)
  def showError(errorMessage: String): Unit = render(new ErrorPage(errorMessage))

  // Commands for executing the pipeline
  def addLoadImageJob(path: String): Unit = pipeline.addJob(new LoadImageJob(path, Some(pipeline)))
  def addLoadRandomImageJob(): Unit = pipeline.addJob(new LoadRandomImageJob(Some(pipeline)))
  def addPredefinedTableConvertJob(table: String): Unit = pipeline.addJob(new PredefinedTableConvertJob(table, Some(pipeline)))
  def addCustomTableConvertJob(customTable: String): Unit = pipeline.addJob(new CustomTableConvertJob(customTable, Some(pipeline)))
  def addFilterRotateJob(angle: Int): Unit = pipeline.addJob(new FilterRotateJob(angle, Some(pipeline)))
  def addFilterInvertJob(): Unit = pipeline.addJob(new FilterInvertJob(Some(pipeline)))
  def addFilterScaleJob(scale: Double): Unit = pipeline.addJob(new FilterScaleJob(scale, Some(pipeline)))
  def addOutputConsoleJob(): Unit = pipeline.addJob(new OutputConsoleJob(Some(pipeline)))
  def addOutputFileJob(path: String): Unit = pipeline.addJob(new OutputFileJob(path, Some(pipeline)))

  /**
   * Runs the pipeline (main execution unit of the app).
   */
  def runPipeline(): Unit = {
    pipeline.run()
  }

  /**
   * Renders a TextPage object.
   *
   * @param renderer The stuff that can render a text.
   */
  private def render(renderer: TextPage): Unit = {
    val output = renderer.render()
    textExporter.export(output)
  }

}
