package app.business.components.loaders.file.image.rgb.specific

import app.business.components.loaders.file.image.rgb.RGBImageLoader
import app.business.components.validators.file.specific.FileFormatValidator
import app.business.models.Image
import app.business.models.pixel.specific.RGBPixel

class PNGImageLoader extends RGBImageLoader {

  override def load(source: String): Image[RGBPixel] = {
    // Check that the source file has png format
    val fileTypeValRes = new FileFormatValidator(Set("png")).validate(source)
    if (fileTypeValRes.hasErrors) throw new IllegalArgumentException("Image does not have png format")

    super.load(source) // Or here can be png-specific loading implementation
  }

}
