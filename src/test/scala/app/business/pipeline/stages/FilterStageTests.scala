package app.business.pipeline.stages

import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.`export`.specific.{OutputConsoleJob, OutputFileJob}
import app.business.pipeline.jobs.filter.specific.{FilterInvertJob, FilterRotateJob, FilterScaleJob}
import app.business.pipeline.stages.specific.{ConvertStage, ExportStage, FilterStage}
import org.scalatest.FunSuite

class FilterStageTests extends FunSuite {

  test("Valid job setup") {
    val stage = new FilterStage

    val valRes = stage.verifyJobSetup()
    assert(!valRes.hasErrors)
  }

  test("Add job") {
    val stage = new FilterStage

    stage.addJob(new FilterScaleJob(2))
    stage.addJob(new FilterScaleJob(0.5))
    stage.addJob(new FilterRotateJob(90))
    stage.addJob(new FilterInvertJob())
    stage.addJob(new FilterRotateJob(180))

    assert(stage.jobs.size === 5)
  }

  test("Run") {
    val stage = new FilterStage

    stage.addJob(new FilterRotateJob(90))
    stage.addJob(new FilterScaleJob(2))

    val image = Image(PointGrid(List(
      List(ASCIIPixel('a'), ASCIIPixel('b')),
      List(ASCIIPixel('c'), ASCIIPixel('d')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('c'), ASCIIPixel('c'), ASCIIPixel('a'), ASCIIPixel('a')),
      List(ASCIIPixel('c'), ASCIIPixel('c'), ASCIIPixel('a'), ASCIIPixel('a')),
      List(ASCIIPixel('d'), ASCIIPixel('d'), ASCIIPixel('b'), ASCIIPixel('b')),
      List(ASCIIPixel('d'), ASCIIPixel('d'), ASCIIPixel('b'), ASCIIPixel('b')))))

    assert(stage.run(image) === expectedImage)
  }

}
