package app.business.pipeline.stages

import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.models.{Image, PointGrid}
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.convert.specific.{CustomTableConvertJob, PredefinedTableConvertJob}
import app.business.pipeline.stages.specific.ConvertStage
import org.scalatest.FunSuite

class ConvertStageTests extends FunSuite {

  test("Invalid job setup - 2 jobs added") {
    val stage = new ConvertStage

    stage.addJob(new CustomTableConvertJob("abc"))
    stage.addJob(new PredefinedTableConvertJob("abc"))

    val valRes = stage.verifyJobSetup()
    assert(valRes.hasErrors)
  }

  test("Valid job setup - 0 jobs added") {
    val stage = new ConvertStage

    val valRes = stage.verifyJobSetup()
    assert(!valRes.hasErrors)
  }

  test("Valid job setup - 1 job added") {
    val stage = new ConvertStage

    stage.addJob(new CustomTableConvertJob("abc"))

    val valRes = stage.verifyJobSetup()
    assert(!valRes.hasErrors)
  }

  test("Add job") {
    val stage = new ConvertStage

    stage.addJob(new CustomTableConvertJob("a"))
    stage.addJob(new CustomTableConvertJob("ab"))
    stage.addJob(new CustomTableConvertJob("abc"))

    assert(stage.jobs.size === 1)
  }

  test("Run") {
    val stage = new ConvertStage

    stage.addJob(new CustomTableConvertJob("a"))

    assert(stage.jobs.size === 1)

    val image = Image(PointGrid(List(List(RGBPixel(1, 2, 3)))))

    val expectedImage = Image(PointGrid(List(List(ASCIIPixel('a')))))

    assert(stage.run(image) === expectedImage)
  }

}
