package app.business.pipeline.jobs.load.specific

import app.business.components.generators.random.image.specific.RandomRGBImageGenerator
import app.business.models.Image
import app.business.models.pixel.specific.RGBPixel
import app.business.pipeline.Pipeline
import app.business.pipeline.jobs.load.LoadJob
import scala.util.Random

class LoadRandomImageJob(val pipeline: Option[Pipeline] = None) extends LoadJob {

  override def execute(input: Unit): Image[RGBPixel] = {
    val generator = new RandomRGBImageGenerator()
    val resultImage = generator.generate()
    resultImage
  }

}
