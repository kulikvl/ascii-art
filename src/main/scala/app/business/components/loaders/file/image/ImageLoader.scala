package app.business.components.loaders.file.image

import app.business.components.loaders.file.FileLoader
import app.business.models.Image
import app.business.models.pixel.Pixel

/**
 * Class representing a loader capable of loading images.
 *
 * @tparam T Type of the pixel of the image that will be loaded.
 */
trait ImageLoader[T <: Pixel] extends FileLoader[Image[T]]
