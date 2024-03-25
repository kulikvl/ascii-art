package app.business.pipeline.jobs.convert

import app.business.components.converters.pixel.pixelToPixel.greyscaleToAscii.specific.GreyscalePixelToAsciiPixelRamp
import app.business.components.converters.pixel.pixelToPixel.rgbToGreyscale.specific.RGBPixelToGreyscalePixelLuminosity
import app.business.models.Image
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.jobs.Job

/**
 * Class representing a job capable of converting RGB images to ASCII images.
 */
abstract class ConvertJob extends Job[Image[RGBPixel], Image[ASCIIPixel]] {

  protected def convert(input: Image[RGBPixel], ramp: String): Image[ASCIIPixel] = {
    // Save the chosen custom table as pipeline artifact
    pipeline match {
      case Some(p) => p.artefacts.put("ramp", ramp)
      case None =>
    }

    // Get greyscale image
    val pixelToGreyscale = new RGBPixelToGreyscalePixelLuminosity
    val greyscaleImage = input.transform(pixelToGreyscale.convert)

    // Get ascii image
    val greyscaleToAscii = new GreyscalePixelToAsciiPixelRamp(ramp)
    val asciiImage = greyscaleImage.transform(greyscaleToAscii.convert)

    asciiImage
  }

}
