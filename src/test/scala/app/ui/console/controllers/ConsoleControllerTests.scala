package app.ui.console.controllers

import app.business.pipeline.SimplePipeline
import app.ui.console.views.pages.text.specific.{ErrorPage, HelpPage, IndexPage}
import exporters.text.stream.StreamTextExporter
import helpers.TestWithFiles
import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class ConsoleControllerTests extends FunSuite with TestWithFiles {

  test("Show help") {
    val stream = new ByteArrayOutputStream()
    val textExporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(textExporter, new SimplePipeline)

    controller.showHelp()

    val text = stream.toString("UTF-8")
    assert(text == new HelpPage().render())

    textExporter.close()
  }

  test("Show index") {
    val stream = new ByteArrayOutputStream()
    val textExporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(textExporter, new SimplePipeline)

    controller.showIndex()

    val text = stream.toString("UTF-8")
    assert(text == new IndexPage().render())

    textExporter.close()
  }

  test("Show error") {
    val stream = new ByteArrayOutputStream()
    val textExporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(textExporter, new SimplePipeline)

    val errMsg = "ERROR HAPPENED!"
    controller.showError(errMsg)

    val text = stream.toString("UTF-8")
    assert(text == new ErrorPage(errMsg).render())

    textExporter.close()
  }

  test("Add job") {
    val stream = new ByteArrayOutputStream()
    val textExporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(textExporter, new SimplePipeline)

    controller.addFilterScaleJob(5.0)
    controller.addFilterScaleJob(5.0)
    controller.addFilterRotateJob(90)
    controller.addOutputFileJob("some/file")
    controller.addLoadImageJob("some/img")

    textExporter.close()
  }

  test("Run empty pipeline") {
    val stream = new ByteArrayOutputStream()
    val textExporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(textExporter, new SimplePipeline)

    controller.runPipeline()

    textExporter.close()
  }

  test("Run valid pipeline") {
    val stream = new ByteArrayOutputStream()
    val textExporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(textExporter, new SimplePipeline)

    controller.addLoadImageJob("src/test/resources/images/gradient.png")
    controller.addOutputFileJob("src/test/resources/output/gradient.txt")
    controller.addCustomTableConvertJob("+++---")

    controller.runPipeline()

    textExporter.close()
  }

}
