package app.business.components.converters.image.imageToString

import app.business.components.converters.Converter
import app.business.models.Image
import app.business.models.pixel.Pixel

/**
 * Converter trait for converting image to string.
 */
trait ImageToString[T <: Pixel] extends Converter[Image[T], String]
