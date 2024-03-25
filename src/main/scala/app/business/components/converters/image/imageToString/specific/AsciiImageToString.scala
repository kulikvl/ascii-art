package app.business.components.converters.image.imageToString.specific

import app.business.components.converters.image.imageToString.ImageToString
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel

/**
 * Converter class for converting ascii image to string.
 */
class AsciiImageToString extends ImageToString[ASCIIPixel] {

  override def convert(image: Image[ASCIIPixel]): String = {
    val h = image.pointGrid.height
    val w = image.pointGrid.width

    (0 until h).map { i =>
      (0 until w).map { j =>
        image.pointGrid.pointAt(j, i).symbol
      }.mkString + "\n"
    }.mkString
  }

}
