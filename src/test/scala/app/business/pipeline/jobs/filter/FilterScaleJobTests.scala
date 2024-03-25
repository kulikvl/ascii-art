package app.business.pipeline.jobs.filter

import app.business.models.{Image, PointGrid}
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.filter.specific.{FilterRotateJob, FilterScaleJob}
import org.scalatest.FunSuite

class FilterScaleJobTests extends FunSuite {

  test("Invalid scale") {
    val pipeline = new SimplePipeline
    val job = new FilterScaleJob(3)
    val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

    assertThrows[IllegalArgumentException](job.execute(image))
  }

  test("2x1 image") {
    val pipeline = new SimplePipeline
    val job = new FilterScaleJob(0.5)
    val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

    val expectedImage = Image(PointGrid(List(List(ASCIIPixel('a')))))

    assert(job.execute(image) === expectedImage)
  }

}
