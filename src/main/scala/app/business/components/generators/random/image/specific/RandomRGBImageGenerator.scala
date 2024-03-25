package app.business.components.generators.random.image.specific

import app.business.components.generators.random.image.RandomImageGenerator
import app.business.components.generators.random.image.specific.RandomRGBImageGenerator.supportedExportFormats
import app.business.models.pixel.specific.RGBPixel
import app.business.models.{Image, PointGrid}
import utils.FileUtils.getFileExtension
import java.awt.image.BufferedImage
import java.awt.{Color, Graphics2D}
import java.io.File
import javax.imageio.ImageIO
import scala.util.Random

/**
 * Class for generating random images based on constructor parameters.
 * Randomness is not absolute. Simple random noise is manifested through the appearance of random circles.
 *
 * @param width Width of the result image.
 * @param height Width of the result image.
 * @param circlesCount How many random circles should be generated.
 * @param maxRadius Maximum radius of the circle.
 * @param exportFile If present, also saves generated image to the specified file.
 */
class RandomRGBImageGenerator(circlesCount: Int = 15, maxRadius: Int = 15, exportFile: Option[File] = None) extends RandomImageGenerator[RGBPixel] {
  require(circlesCount > 0 && circlesCount < 50, "Circles must be grater than 0 and less than 50")
  require(maxRadius > 5 && maxRadius < 20, "maxRadius must be grater than 5 and less than 15")

  override def generate(): Image[RGBPixel] = {
    // Create random height and width in [100, 500]
    val height = Random.nextInt(400) + 100
    val width = Random.nextInt(400) + 100

    val image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val g: Graphics2D = image.createGraphics()

    // Fill background with black
    g.setColor(Color.BLACK)
    g.fillRect(0, 0, width, height)

    // Generate simple noise with random circles
    for (_ <- 1 to circlesCount) {
      val radius = Random.nextInt(maxRadius) + 5
      val color = new Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
      val x = Random.nextInt(width - 2 * radius) + radius
      val y = Random.nextInt(height - 2 * radius) + radius

      g.setColor(color)
      g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius)
    }

    g.dispose()

    exportFile match {
      case Some(file) =>
        val optionFormat = getFileExtension(file.getName)

        optionFormat match {
          case Some(format) =>
            if (!supportedExportFormats.contains(format)) throw new IllegalArgumentException("Provided export file's format is not supported")
            ImageIO.write(image, format, file)

          case None => throw new IllegalArgumentException("Provided export file does not have format")
        }

      case None =>
    }

    Image(PointGrid((0 until image.getHeight).toList.map { y =>
      (0 until image.getWidth).toList.map { x =>
        val color = new Color(image.getRGB(x, y))
        RGBPixel(color.getRed, color.getGreen, color.getBlue)
      }
    }))
  }

}

object RandomRGBImageGenerator {
  val supportedExportFormats: Set[String] = Set("png", "jpg", "bmp")
}
