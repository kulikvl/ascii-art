package app.business.pipeline.jobs.`export`

import app.business.components.converters.image.imageToString.specific.AsciiImageToString
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.jobs.Job

/**
 * Class representing a job capable of exporting ASCII images.
 */
abstract class ExportJob extends Job[Image[ASCIIPixel], Unit] {

  def convertAsciiImageToString(image: Image[ASCIIPixel]): String = {
    val asciiImageToString = new AsciiImageToString
    asciiImageToString.convert(image)
  }

}
