package app.business.components.converters.pixel.pixelToPixel.greyscaleToAscii

import app.business.components.converters.pixel.pixelToPixel.PixelToPixel
import app.business.models.pixel.specific.{ASCIIPixel, GreyscalePixel}

trait GreyscalePixelToAsciiPixel extends PixelToPixel[GreyscalePixel, ASCIIPixel]
