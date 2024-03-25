package app.business.pipeline.jobs.load

import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.load.specific.LoadImageJob
import org.scalatest.FunSuite

class LoadImageJobTests extends FunSuite {

  test("Invalid source file - Empty name") {
    val job = new LoadImageJob("")
    assertThrows[IllegalArgumentException](job.execute())
  }

  test("Invalid source file - Unsupported extension") {
    val job = new LoadImageJob("src/test/resources/images/weird.abc")
    assertThrows[IllegalArgumentException](job.execute())
  }

  test("Valid source file - png extension") {
    val job = new LoadImageJob("src/test/resources/images/meme.png")
    val image = job.execute()

    assert(image.pointGrid.height === 441)
    assert(image.pointGrid.width === 329)
  }

  test("Valid source file - jpg extension") {
    val job = new LoadImageJob("src/test/resources/images/starbucks.jpg")
    val image = job.execute()

    assert(image.pointGrid.height === 853)
    assert(image.pointGrid.width === 640)
  }

}
