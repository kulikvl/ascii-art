package app.business.pipeline.stages.specific

import app.business.components.validators.ValidationResult
import app.business.models.Image
import app.business.models.pixel.specific.ASCIIPixel
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.stages.{SimpleStage, Stage}

/**
 * Class representing a stage that contains jobs capable of exporting ASCII images.
 */
class ExportStage extends SimpleStage[Image[ASCIIPixel], Unit] {

  override def verifyJobSetup(): ValidationResult = {
    val jobsCount = jobs.size
    ValidationResult(hasErrors = jobsCount == 0, message = s"At least 1 job (use export command) should be in export stage")
  }

  override def run(input: Image[ASCIIPixel]): Unit = {
    jobs.foreach(_.execute(input))
  }

}
