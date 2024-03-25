package app.business.components.filters.image.ascii.specific

import app.business.components.filters.image.ascii.AsciiImageFilter
import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}

/**
 * Filter for scaling ascii image.
 *
 * @param scale Scale volume, should be one of the following values: 0.5, 1, 2.
 */
class ScaleFilter(scale: Double) extends AsciiImageFilter {
  private val allowedValues = Set(0.5, 1.0, 2.0)
  require(allowedValues.contains(scale), "Scale should one of the following: 0.5, 1, 2")

  override def apply(targetImage: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    scale match {
      case 0.5 => scaleHalf(targetImage)
      case 2 => scaleDouble(targetImage)
      case 1 => targetImage
    }
  }

  private def scaleDouble(targetImage: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    val newPixels = for {
      row <- targetImage.pointGrid.points
      duplicatedRow = row.flatMap(char => Seq(char, char))
    } yield Seq(duplicatedRow, duplicatedRow)

    Image(PointGrid(newPixels.flatten))
  }

  private def scaleHalf(targetImage: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    // Downsample rows: Take every first row of the two
    val halfRows: Seq[Seq[ASCIIPixel]] = targetImage.pointGrid.points.grouped(2).map {
      case Seq(row1, _*) => row1 // Take the first row, ignore the rest
      case row => row.headOption.getOrElse(List.empty) // Handle the case where there's only one row
    }.toSeq

    // Downsample columns: Take every first column of the two from the taken rows
    val newPixels: Seq[Seq[ASCIIPixel]] = halfRows.map { row =>
      row.grouped(2).map {
        case Seq(pixel1, _*) => pixel1 // Take the first pixel, ignore the rest
        case pixel => pixel.headOption.getOrElse(ASCIIPixel(' ')) // Handle the case where there's only one pixel
      }.toSeq
    }

    Image(PointGrid(newPixels))
  }

}
