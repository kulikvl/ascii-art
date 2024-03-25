package exporters.text.stream

import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class StreamTextExporterTests extends FunSuite {

  test("Write") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.export("Hello World")

    assert(stream.toString("UTF-8") === "Hello World")

    exporter.close()
  }

  test("Write after close") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    exporter.close()

    assertThrows[RuntimeException](exporter.export("Should throw anyways"))
  }

}

