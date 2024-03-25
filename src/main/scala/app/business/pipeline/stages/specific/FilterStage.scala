package app.business.pipeline.stages.specific

import app.business.components.validators.ValidationResult
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.stages.{SimpleStage, Stage}

/**
 * Class representing a stage that contains jobs capable of filtering ASCII images.
 */
class FilterStage extends SimpleStage[Image[ASCIIPixel], Image[ASCIIPixel]] {

  override def verifyJobSetup(): ValidationResult = {
    ValidationResult(hasErrors = false, message = "Arbitrary amount of filter jobs can be sequentially executed")
  }

  override def run(input: Image[ASCIIPixel]): Image[ASCIIPixel] = {
    jobs.foldLeft(input) { (acc, job) => job.execute(acc) }
  }

}
