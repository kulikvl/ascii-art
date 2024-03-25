package app.business.pipeline.jobs.convert

import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.models.{Image, PointGrid}
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.convert.specific.CustomTableConvertJob
import org.scalatest.FunSuite

class CustomTableConvertJobTests extends FunSuite {

  test("Empty custom ramp") {
    val job = new CustomTableConvertJob("")
    val image = Image(PointGrid(List(List(RGBPixel(1, 2, 3), RGBPixel(1, 2, 3)))))

    assertThrows[IllegalArgumentException](job.execute(image))
  }

  test("Ramp artefact is saved") {
    val pipeline = new SimplePipeline
    val job = new CustomTableConvertJob("some-custom-ramp", Some(pipeline))
    val image = Image(PointGrid(List(List(RGBPixel(1, 2, 3), RGBPixel(1, 2, 3)))))

    assert(pipeline.artefacts.get("ramp") === None)
    job.execute(image)
    assert(pipeline.artefacts.get("ramp") === Some("some-custom-ramp"))
  }

  test("1x1 image, 1-symbol ramp") {
    val job = new CustomTableConvertJob("a")
    val rgbImage = Image(PointGrid(List(List(RGBPixel(1, 2, 3)))))

    val expectedAsciiImage = Image(PointGrid(List(List(ASCIIPixel('a')))))

    assert(job.execute(rgbImage) === expectedAsciiImage)
  }

  test("2x1 image, 2-symbols ramp") {
    val job = new CustomTableConvertJob("ab")
    val rgbImage = Image(PointGrid(List(List(RGBPixel(1, 2, 3), RGBPixel(200, 255, 56)))))

    val expectedAsciiImage = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

    assert(job.execute(rgbImage) === expectedAsciiImage)
  }

}
