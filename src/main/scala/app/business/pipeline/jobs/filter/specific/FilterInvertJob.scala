package app.business.pipeline.jobs.filter.specific

import app.business.components.filters.image.ascii.specific.InvertFilter
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.filter.FilterJob

/**
 *
 * Requires ramp artefact if has defined pipeline, otherwise uses the default ramp.
 */
class FilterInvertJob(val pipeline: Option[Pipeline] = None) extends FilterJob {

  private def getArtefactRamp: String = {
    val defaultRamp = """@%#*+=-:. """ // bourke-small

    val artefactRampOption = pipeline match {
      case Some(p) => p.artefacts.get("ramp")
      case None => return defaultRamp
    }

    artefactRampOption match {
      case Some(value) =>
        value match {
          case v: String => v
          case _ => defaultRamp
        }
      case None => defaultRamp
    }
  }

  override def execute(input: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    val invertFilter = new InvertFilter(getArtefactRamp)
    val resultImage = invertFilter.apply(input)
    resultImage
  }

}
