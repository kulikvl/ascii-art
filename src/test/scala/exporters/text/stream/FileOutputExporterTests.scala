package exporters.text.stream

import helpers.TestWithFiles
import org.scalatest.FunSuite

import java.io.{ByteArrayOutputStream, File}

class FileOutputExporterTests extends FunSuite with TestWithFiles {

  test("No file exists") {
    val fileName = getTestFile()

    try {
      ensureDeleted(fileName)

      val file = new File(fileName)
      val exporter = new FileOutputExporter(file)

      exporter.export("Hello World")
      exporter.close()

      assertFileContent(fileName, "Hello World")
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("File already exists") {
    val fileName = getTestFile()

    try {
      ensureCreated(fileName)

      val file = new File(fileName)
      val exporter = new FileOutputExporter(file)

      exporter.export("Hello World")
      exporter.close()

      assertFileContent(fileName, "Hello World")
    }
    finally{
      ensureDeleted(fileName)
    }
  }

  test("Write after close") {
    val fileName = getTestFile()

    try {
      ensureDeleted(fileName)

      val file = new File(fileName)
      val exporter = new FileOutputExporter(file)

      exporter.export("Before close")

      exporter.close()

      assertThrows[RuntimeException](exporter.export("After close"))
    }
    finally {
      ensureDeleted(fileName)
    }
  }

}
