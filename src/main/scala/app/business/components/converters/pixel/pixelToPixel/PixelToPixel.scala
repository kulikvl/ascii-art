package app.business.components.converters.pixel.pixelToPixel

import app.business.components.converters.Converter
import app.business.models.pixel.Pixel

trait PixelToPixel[T <: Pixel, A <: Pixel] extends Converter[T, A]
