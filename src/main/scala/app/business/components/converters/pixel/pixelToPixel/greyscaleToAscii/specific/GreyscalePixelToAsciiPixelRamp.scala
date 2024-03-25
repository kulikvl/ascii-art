package app.business.components.converters.pixel.pixelToPixel.greyscaleToAscii.specific

import app.business.components.converters.pixel.pixelToPixel.greyscaleToAscii.GreyscalePixelToAsciiPixel
import app.business.models.pixel.specific.{ASCIIPixel, GreyscalePixel}

/**
 * Class for converting greyscale pixel to its corresponding ASCII character pixel.
 *
 * @param ramp ramp (1D table) that is used in mapping pixel to ascii.
 */
class GreyscalePixelToAsciiPixelRamp(ramp: String) extends GreyscalePixelToAsciiPixel {
  require(ramp.nonEmpty, "The ramp can't be empty")

  override def convert(greyscalePixel: GreyscalePixel): ASCIIPixel = {
    val rescaledGreyscale = ((greyscalePixel.intensity / 255.0) * (ramp.length - 1)).round.toInt

    ASCIIPixel(ramp(rescaledGreyscale))
  }

}
