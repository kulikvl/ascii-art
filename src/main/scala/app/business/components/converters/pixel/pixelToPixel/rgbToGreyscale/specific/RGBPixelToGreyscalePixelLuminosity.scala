package app.business.components.converters.pixel.pixelToPixel.rgbToGreyscale.specific

import app.business.components.converters.pixel.pixelToPixel.rgbToGreyscale.RGBPixelToGreyscalePixel
import app.business.models.pixel.specific.{GreyscalePixel, RGBPixel}

class RGBPixelToGreyscalePixelLuminosity extends RGBPixelToGreyscalePixel {

  override def convert(pixel: RGBPixel): GreyscalePixel = GreyscalePixel((0.3 * pixel.r + 0.59 * pixel.g + 0.11 * pixel.b).round.toInt)

}
