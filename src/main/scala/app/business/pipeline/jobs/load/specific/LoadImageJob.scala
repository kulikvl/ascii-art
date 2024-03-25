package app.business.pipeline.jobs.load.specific

import app.business.components.loaders.file.image.rgb.RGBImageLoader
import app.business.models.Image
import app.business.models.pixel.specific.RGBPixel
import app.business.pipeline.{Pipeline, SimplePipeline}
import app.business.pipeline.jobs.load.LoadJob
import utils.FileUtils.getFileExtension

class LoadImageJob(source: String, val pipeline: Option[Pipeline] = None) extends LoadJob {

  override def execute(input: Unit): Image[RGBPixel] = {
    val formatOption = getFileExtension(source)

    val loader = formatOption match {
      case Some(format) =>
        RGBImageLoader.getLoaderFor(format) match {
          case Some(loader) => loader
          case None => throw new IllegalArgumentException("There is no loader for the specified image format")
        }
      case None => throw new IllegalArgumentException("Specified source image file does not have a format")
    }

    val loadedImage = loader.load(source)
    loadedImage
  }

}
