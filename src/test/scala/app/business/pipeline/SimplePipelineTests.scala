package app.business.pipeline

import app.business.pipeline.jobs.`export`.specific.{OutputConsoleJob, OutputFileJob}
import app.business.pipeline.jobs.convert.specific.CustomTableConvertJob
import app.business.pipeline.jobs.filter.specific.FilterScaleJob
import app.business.pipeline.jobs.load.specific.LoadImageJob
import helpers.TestWithFiles
import org.scalatest.FunSuite

class SimplePipelineTests extends FunSuite with TestWithFiles {

  test("Add job") {
    val pipeline = new SimplePipeline

    pipeline.addJob(new LoadImageJob("image.png", Some(pipeline)))
    pipeline.addJob(new OutputConsoleJob(Some(pipeline)))

    assert(pipeline.jobs.size === 2)
  }

  test("Invalid job setup") {
    val pipeline = new SimplePipeline

    pipeline.addJob(new LoadImageJob("image.png", Some(pipeline)))

    val valRes = pipeline.verifyJobSetup()
    assert(valRes.hasErrors)
  }

  test("Valid job setup") {
    val pipeline = new SimplePipeline

    pipeline.addJob(new LoadImageJob("image.png", Some(pipeline)))
    pipeline.addJob(new CustomTableConvertJob("+++---", Some(pipeline)))
    pipeline.addJob(new OutputConsoleJob(Some(pipeline)))

    val valRes = pipeline.verifyJobSetup()
    assert(!valRes.hasErrors)
  }

  test("Run pipeline") {
    val pipeline = new SimplePipeline

    pipeline.addJob(new LoadImageJob("src/test/resources/images/4pixels.png", Some(pipeline)))
    pipeline.addJob(new CustomTableConvertJob("A", Some(pipeline)))
    pipeline.addJob(new FilterScaleJob(0.5, Some(pipeline)))
    pipeline.addJob(new OutputFileJob("src/test/resources/output/4pixels.txt", Some(pipeline)))

    pipeline.run()

    assertFileContent("src/test/resources/output/4pixels.txt", "A\n")
  }

}
