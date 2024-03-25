package app.business.components.filters.image.ascii.specific

import app.business.components.converters.pixel.pixelToPixel.asciiToAscii.specific.AsciiPixelToInvertedAsciiPixel
import app.business.components.filters.image.ascii.AsciiImageFilter
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel

/**
 * Filter for inverting ascii image.
 */
class InvertFilter(ramp: String) extends AsciiImageFilter {
  require(ramp.nonEmpty, "The ramp can't be empty")

  override def apply(targetImage: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    targetImage.transform(new AsciiPixelToInvertedAsciiPixel(ramp).convert)
  }

}
