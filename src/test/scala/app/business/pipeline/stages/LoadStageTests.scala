package app.business.pipeline.stages

import app.business.models.{Image, PointGrid}
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.load.specific.LoadImageJob
import app.business.pipeline.stages.specific.{ConvertStage, LoadStage}
import org.scalatest.FunSuite

class LoadStageTests extends FunSuite {

  test("Invalid job setup") {
    val stage = new LoadStage

    val valRes = stage.verifyJobSetup()
    assert(valRes.hasErrors)
  }

  test("Valid job setup") {
    val pipeline = new SimplePipeline
    val stage = new LoadStage

    stage.addJob(new LoadImageJob("img.png"))

    val valRes = stage.verifyJobSetup()
    assert(!valRes.hasErrors)
  }

  test("Add job") {
    val pipeline = new SimplePipeline
    val stage = new LoadStage

    stage.addJob(new LoadImageJob("img.png"))
    stage.addJob(new LoadImageJob("img2.png"))
    stage.addJob(new LoadImageJob("img3.png"))

    assert(stage.jobs.size === 1)
  }

  test("Run") {
    val pipeline = new SimplePipeline
    val stage = new LoadStage

    stage.addJob(new LoadImageJob("src/test/resources/images/4pixels.png"))

    val expectedImage = Image(PointGrid(List(
      List(RGBPixel(255, 0, 0), RGBPixel(0, 255, 0)),
      List(RGBPixel(0, 0, 255), RGBPixel(255, 255, 0)))))

    assert(stage.run() === expectedImage)
  }

}
