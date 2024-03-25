package app.business.components.converters.pixel.pixelToPixel.asciiToAscii.specific

import app.business.components.converters.pixel.pixelToPixel.asciiToAscii.AsciiPixelToAsciiPixel
import app.business.models.pixel.specific.ASCIIPixel

class AsciiPixelToInvertedAsciiPixel(ramp: String) extends AsciiPixelToAsciiPixel {
  require(ramp.nonEmpty, "The ramp can't be empty")

  override def convert(pixel: ASCIIPixel): ASCIIPixel = {
    require(ramp.contains(pixel.symbol), "The ramp must contain an ASCII symbol to represent the converted pixel")
    if (ramp.length == 1) return ASCIIPixel(ramp(0))

    // Greyscale should be in [0, 255]
    val greyscale = ((ramp.indexOf(pixel.symbol) / (ramp.length - 1).toDouble) * 255).round.toInt

    // Greyscale value rescaled to ramp range. The result should be in [0, ramp.length - 1]
    val rescaledGreyscale = ( (greyscale / 255.0) * (ramp.length - 1) ).round.toInt

    val invertedIndex = (ramp.length - 1) - rescaledGreyscale

    ASCIIPixel(ramp(invertedIndex))
  }

}
