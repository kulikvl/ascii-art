package app.business.pipeline.jobs.`export`.specific

import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.Pipeline
import app.business.pipeline.jobs.`export`.ExportJob
import exporters.text.stream.FileOutputExporter
import java.io.File

class OutputFileJob(filename: String, val pipeline: Option[Pipeline] = None) extends ExportJob {

  override def execute(image: Image[ASCIIPixel]): Unit = {
    val file = new File(filename)
    val exporter = new FileOutputExporter(file)

    exporter.export(convertAsciiImageToString(image))
  }

}
