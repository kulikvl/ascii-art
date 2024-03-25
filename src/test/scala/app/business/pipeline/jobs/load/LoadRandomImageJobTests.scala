package app.business.pipeline.jobs.load

import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.load.specific.{LoadImageJob, LoadRandomImageJob}
import org.scalatest.FunSuite

class LoadRandomImageJobTests extends FunSuite {

  test("Load random image - no exceptions") {
    val job = new LoadRandomImageJob()
    job.execute()
  }

}
