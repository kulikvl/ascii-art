package app.business.pipeline.stages

import app.business.models.{Image, PointGrid}
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.`export`.specific.{OutputConsoleJob, OutputFileJob}
import app.business.pipeline.jobs.convert.specific.CustomTableConvertJob
import app.business.pipeline.stages.specific.{ConvertStage, ExportStage}
import helpers.TestWithFiles
import org.scalatest.FunSuite

class ExportStageTests extends FunSuite with TestWithFiles {

  test("Invalid job setup") {
    val stage = new ExportStage

    val valRes = stage.verifyJobSetup()
    assert(valRes.hasErrors)
  }

  test("Valid job setup") {
    val stage = new ExportStage

    stage.addJob(new OutputConsoleJob())

    val valRes = stage.verifyJobSetup()
    assert(!valRes.hasErrors)
  }

  test("Add job") {
    val stage = new ExportStage

    stage.addJob(new OutputConsoleJob())
    stage.addJob(new OutputConsoleJob())
    stage.addJob(new OutputFileJob("path/to/file"))

    assert(stage.jobs.size === 3)
  }

  test("Run") {
    val fileName = getTestFile()

    try {
      ensureDeleted(fileName)

      val stage = new ExportStage

      stage.addJob(new OutputFileJob(fileName))

      val inputImage = Image(PointGrid(List(List(ASCIIPixel('a')))))

      stage.run(inputImage)

      assertFileContent(fileName, "a\n")
    }
    finally {
      ensureDeleted(fileName)
    }
  }

}
