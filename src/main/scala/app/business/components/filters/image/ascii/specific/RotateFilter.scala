package app.business.components.filters.image.ascii.specific

import app.business.components.filters.image.ascii.AsciiImageFilter
import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}

/**
 * Filter for rotating ascii image.
 *
 * @param angle Angle of rotation, should be a multiple of 90.
 */
class RotateFilter(val angle: Int) extends AsciiImageFilter {
  require(angle % 90 == 0, "The angle must be a multiple of 90.")

  override def apply(targetImage: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    // Normalize the angle to be within [0, 360)
    val rotationAngle = ((angle % 360) + 360) % 360 // This also handles negative angles

    // Determine the number of 90-degree rotations needed
    val numberOfRotations = rotationAngle / 90

    // Apply the rotations
    (1 to numberOfRotations).foldLeft(targetImage) { (currentImage, _) =>
      rotate90(currentImage)
    }
  }

  /**
   * Rotates the image by 90 degrees.
   *
   * @param targetImage Image for rotation.
   * @return Rotated image.
   */
  private def rotate90(targetImage: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    def rotateClockwise(grid: Seq[Seq[ASCIIPixel]]): Seq[Seq[ASCIIPixel]] = {
      grid.transpose.map(_.reverse)
    }

    val grid = targetImage.pointGrid.points
    val rotatedGrid = rotateClockwise(grid)

    Image(PointGrid(rotatedGrid))
  }

}
