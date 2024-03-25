package app.business.pipeline.jobs.filter

import app.business.models.{Image, PointGrid}
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.filter.specific.{FilterInvertJob, FilterRotateJob}
import org.scalatest.FunSuite

class FilterRotateJobTests extends FunSuite {

  test("Invalid angle") {
    val job = new FilterRotateJob(95)
    val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

    assertThrows[IllegalArgumentException](job.execute(image))
  }

  test("2x1 image") {
    val job = new FilterRotateJob(90)
    val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

    val expectedImage = Image(PointGrid(List(List(ASCIIPixel('a')), List(ASCIIPixel('b')))))

    assert(job.execute(image) === expectedImage)
  }

}
