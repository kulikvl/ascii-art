package app.business.pipeline.jobs.convert.specific

import app.business.models.Image
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.convert.ConvertJob

class CustomTableConvertJob(val customRamp: String, val pipeline: Option[Pipeline] = None) extends ConvertJob {

  override def execute(input: Image[RGBPixel]): Image[ASCIIPixel] = {
    convert(input, customRamp)
  }

}
