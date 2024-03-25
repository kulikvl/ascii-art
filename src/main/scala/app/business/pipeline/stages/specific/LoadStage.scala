package app.business.pipeline.stages.specific

import app.business.components.validators.ValidationResult
import app.business.models.Image
import app.business.models.pixel.specific.RGBPixel
import app.business.pipeline.jobs.Job
import app.business.pipeline.jobs.load.LoadJob
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.stages.{SimpleStage, Stage, UniqueStage}

/**
 * Class representing a stage that contains jobs capable of loading rgb images.
 */
class LoadStage extends UniqueStage[Unit, Image[RGBPixel]] {

  override def verifyJobSetup(): ValidationResult = {
    val jobsCount = jobs.size
    ValidationResult(hasErrors = jobsCount != 1, message = s"Only 1 job (use load command) should be in the load stage")
  }

  override def run(input: Unit = ()): Image[RGBPixel] = {
    jobs.head.execute(input)
  }

}
