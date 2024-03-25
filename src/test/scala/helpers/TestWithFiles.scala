package helpers

import org.scalatest.FunSuite

import java.io.File
import java.util.UUID
import scala.io.Source

trait TestWithFiles extends FunSuite {
  val testFolder = "src/test/resources/tmp/"

  def ensureDeleted(filePath: String): Unit = {
    val file = new File(filePath)

    if (file.exists())
      if (!file.delete())
        throw new RuntimeException("Could not delete " + filePath)
  }

  def ensureCreated(filePath: String): Unit = {
    val file = new File(filePath)

    ensureDeleted(filePath)

    if (!file.createNewFile())
      throw new RuntimeException("Could not create " + filePath)
  }

  def getTestFile(format: String = "txt"): String = testFolder + UUID.randomUUID().toString + (if (format.nonEmpty) s".$format")

  def assertFileContent(filePath: String, content: String): Unit = {
    val source = Source.fromFile(filePath)
    val text = source.mkString

    source.close()

    assert(text === content)
  }
}
