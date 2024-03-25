package app.business.components.loaders.file.image.rgb

import app.business.components.loaders.file.image.ImageLoader
import app.business.components.loaders.file.image.rgb.specific.{JPGImageLoader, PNGImageLoader}
import app.business.components.validators.file.specific.FilePathValidator
import app.business.models.pixel.specific.RGBPixel
import app.business.models.{Image, PointGrid}

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Class for loading RGB Pixel images from the source (path).
 */
abstract class RGBImageLoader extends ImageLoader[RGBPixel] {

  override def load(source: String): Image[RGBPixel] = {
    // Check if the source exists and it is a file
    val filePathValRes = new FilePathValidator().validate(source)
    if (filePathValRes.hasErrors) throw new IllegalArgumentException(s"Failed loading image: ${filePathValRes.message}")

    var image: BufferedImage = null

    try {
      val imageFile = new File(source)
      image = ImageIO.read(imageFile)
      if (image == null) throw new RuntimeException("ImageIO.read returned null")
    } catch {
      case e: Exception => throw new IllegalArgumentException(s"Error loading image from source $source, error: ${e.getMessage}")
    }

    val w = image.getWidth
    val h = image.getHeight

    val imageArray = for (y <- 0 until h) yield {
      for (x <- 0 until w) yield {
        val color = image.getRGB(x, y)
        // Extracting colors
        RGBPixel((color & 0xff0000) >> 16, (color & 0xff00) >> 8, color & 0xff)
      }
    }

    Image(new PointGrid[RGBPixel](imageArray))
  }

}

object RGBImageLoader {
  val supportedFormats: Set[String] = Set("png", "jpg")

  /**
   * Returns loader for the specified image format. If none exists, returns None.
   */
  def getLoaderFor(format: String): Option[RGBImageLoader] = {
    if (!supportedFormats.contains(format)) return None

    format match {
      case "png" => Some(new PNGImageLoader)
      case "jpg" => Some(new JPGImageLoader)
    }
  }
}
