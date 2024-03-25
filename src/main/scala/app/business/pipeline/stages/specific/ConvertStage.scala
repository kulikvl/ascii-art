package app.business.pipeline.stages.specific

import app.business.components.validators.ValidationResult
import app.business.models.Image
import app.business.models.pixel.specific.{ASCIIPixel, RGBPixel}
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.convert.specific.PredefinedTableConvertJob
import app.business.pipeline.stages.{SimpleStage, Stage, UniqueStage}

/**
 * Class representing a stage that contains jobs capable of converting rgb images to ascii images.
 */
class ConvertStage extends UniqueStage[Image[RGBPixel], Image[ASCIIPixel]] {

  override def verifyJobSetup(): ValidationResult = {
    val jobsCount = jobs.size
    ValidationResult(hasErrors = jobsCount > 1, message = s"Only 0..1 job (use convert command) should be in the conversion stage")
  }

  override def run(input: Image[RGBPixel]): Image[ASCIIPixel] = {
    if (jobs.isEmpty) addJob(new PredefinedTableConvertJob("bourke-small"))
    jobs.head.execute(input)
  }

}
