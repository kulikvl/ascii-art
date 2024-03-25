package app.business.pipeline.jobs.filter.specific

import app.business.components.filters.image.ascii.specific.ScaleFilter
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.filter.FilterJob
import app.business.pipeline.stages.specific.FilterStage

class FilterScaleJob(scale: Double, val pipeline: Option[Pipeline] = None) extends FilterJob {

  override def execute(input: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    val scaleFilter = new ScaleFilter(scale)
    val resultImage = scaleFilter.apply(input)
    resultImage
  }

}
