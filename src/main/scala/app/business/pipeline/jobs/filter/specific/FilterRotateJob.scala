package app.business.pipeline.jobs.filter.specific

import app.business.components.filters.image.ascii.specific.RotateFilter
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.filter.FilterJob

class FilterRotateJob(angle: Int, val pipeline: Option[Pipeline] = None) extends FilterJob {

  override def execute(input: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    val rotateFilter = new RotateFilter(angle)
    val resultImage = rotateFilter.apply(input)
    resultImage
  }

}
