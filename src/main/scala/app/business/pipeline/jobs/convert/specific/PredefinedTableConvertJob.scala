package app.business.pipeline.jobs.convert.specific

import app.business.models.Image
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.convert.ConvertJob

class PredefinedTableConvertJob(val predefinedTable: String, val pipeline: Option[Pipeline] = None) extends ConvertJob {
  // Paul Bourke standard character ramp (table), black -> white. Ramp length = 70
  private val bourkeStandardRamp = """$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,"^`\'. """
  private val bourkeSmallRamp = """@%#*+=-:. """
  private val nonLinearRamp = """@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%++++****====::::    """
  private val alphabetRamp = """ABCDEFGHIJKLMNOPQRSTUVWXYZ"""

  override def execute(input: Image[RGBPixel]): Image[ASCIIPixel] = {
    val ramp = predefinedTable match {
      case "bourke-standard" => bourkeStandardRamp
      case "bourke-small" => bourkeSmallRamp
      case "alphabet" => alphabetRamp
      case "non-linear" => nonLinearRamp
      case _ => throw new IllegalArgumentException(s"Error creating predefinedTableConvertJob - no such predefined table: $predefinedTable")
    }

    convert(input, ramp)
  }

}
