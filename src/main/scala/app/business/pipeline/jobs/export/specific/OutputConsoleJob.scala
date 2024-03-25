package app.business.pipeline.jobs.`export`.specific

import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.Pipeline
import app.business.pipeline.jobs.`export`.ExportJob
import exporters.text.stream.StdOutputExporter

class OutputConsoleJob(val pipeline: Option[Pipeline] = None) extends ExportJob {

  override def execute(image: Image[ASCIIPixel]): Unit = {
    val exporter = new StdOutputExporter

    exporter.export(convertAsciiImageToString(image))
  }

}
