package app.business.pipeline.jobs.convert

import app.business.models.{Image, PointGrid}
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.convert.specific.{CustomTableConvertJob, PredefinedTableConvertJob}
import org.scalatest.FunSuite

class PredefinedTableConvertJobTests extends FunSuite {

  test("Invalid custom table") {
    val job = new PredefinedTableConvertJob("non-existing-custom-table")
    val image = Image(PointGrid(List(List(RGBPixel(1, 2, 3), RGBPixel(1, 2, 3)))))

    assertThrows[IllegalArgumentException](job.execute(image))
  }

  test("2x1 image, bourke-small ramp") {
    val pipeline = new SimplePipeline
    val job = new PredefinedTableConvertJob("bourke-small")
    val rgbImage = Image(PointGrid(List(List(RGBPixel(1, 2, 3), RGBPixel(250, 255, 230)))))

    val expectedAsciiImage = Image(PointGrid(List(List(ASCIIPixel('@'), ASCIIPixel(' ')))))

    assert(job.execute(rgbImage) === expectedAsciiImage)
  }

}
