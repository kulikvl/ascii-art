package app.business.pipeline.jobs.`export`

import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}
import app.business.pipeline.SimplePipeline
import app.business.pipeline.jobs.`export`.specific.OutputFileJob
import helpers.TestWithFiles
import org.scalatest.FunSuite
import java.io.FileNotFoundException

class OutputFileJobTests extends FunSuite with TestWithFiles {

  test("Invalid export file") {
    val job = new OutputFileJob("")
    val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

    assertThrows[FileNotFoundException](job.execute(image))
  }

  test("No export file exists") {
    val fileName = getTestFile()

    try {
      ensureDeleted(fileName)

      val job = new OutputFileJob(fileName)
      val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

      job.execute(image)
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Export file already exists") {
    val fileName = getTestFile()

    try {
      ensureDeleted(fileName)

      val job = new OutputFileJob(fileName)
      val image = Image(PointGrid(List(List(ASCIIPixel('a'), ASCIIPixel('b')))))

      job.execute(image)
    }
    finally {
      ensureDeleted(fileName)
    }
  }

}
