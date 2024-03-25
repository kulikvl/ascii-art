package exporters.text.stream

import exporters.text.TextExporter

import java.io.OutputStream

/**
 * Class used to export text data to a specific OutputStream.
 */
class StreamTextExporter[T](outputStream: OutputStream) extends TextExporter {
  private var closed = false

  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }

  override def export(text: String): Unit = {
    if (closed)
      throw new RuntimeException("The stream is already closed")

    outputStream.write(text.getBytes("UTF-8"))
    outputStream.flush()
  }

}
