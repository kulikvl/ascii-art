package app.business.pipeline.jobs.filter

import app.business.models.{Image, PointGrid}
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.filter.specific.FilterInvertJob
import org.scalatest.FunSuite

class FilterInvertJobTests extends FunSuite {

  test("2x1 image") {
    val pipeline = new SimplePipeline
    val job = new FilterInvertJob(Some(pipeline))
    val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

    val expectedImage = Image(PointGrid(List(List(ASCIIPixel('b'), ASCIIPixel('a')))))

    pipeline.artefacts.put("ramp", "ab")
    assert(job.execute(image) === expectedImage)
  }

}
