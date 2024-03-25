package app.business.components.generators.random.image

import app.business.components.generators.random.RandomGenerator
import app.business.models.Image
import app.business.models.pixel.Pixel

/**
 * Class representing a random generator capable of generating random images.
 *
 * @tparam T Type of the pixel of the random image that will be generated.
 */
trait RandomImageGenerator[T <: Pixel] extends RandomGenerator[Image[T]]

