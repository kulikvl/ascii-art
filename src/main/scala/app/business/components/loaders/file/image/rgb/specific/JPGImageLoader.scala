package app.business.components.loaders.file.image.rgb.specific

import app.business.components.loaders.file.image.rgb.RGBImageLoader
import app.business.components.validators.file.specific.FileFormatValidator
import app.business.models.Image
import app.business.models.pixel.specific.RGBPixel

class JPGImageLoader extends RGBImageLoader {

  override def load(source: String): Image[RGBPixel] = {
    // Check that the source file has jpg format
    val fileTypeValRes = new FileFormatValidator(Set("jpg")).validate(source)
    if (fileTypeValRes.hasErrors) throw new IllegalArgumentException("Image does not have jpg format")

    super.load(source) // Or here can be jpg-specific loading implementation
  }

}
