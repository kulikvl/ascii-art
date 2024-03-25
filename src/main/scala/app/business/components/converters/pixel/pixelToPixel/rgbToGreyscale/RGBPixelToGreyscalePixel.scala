package app.business.components.converters.pixel.pixelToPixel.rgbToGreyscale

import app.business.components.converters.pixel.pixelToPixel.PixelToPixel
import app.business.models.pixel.specific.{GreyscalePixel, RGBPixel}

trait RGBPixelToGreyscalePixel extends PixelToPixel[RGBPixel, GreyscalePixel]
