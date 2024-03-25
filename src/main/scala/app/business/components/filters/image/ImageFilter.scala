package app.business.components.filters.image

import app.business.components.filters.Filter
import app.business.models.Image
import app.business.models.pixel.Pixel

/**
 * Class representing a filter capable of filtering an image.
 *
 * @tparam T Type of the pixel of the image that would be filtered.
 */
trait ImageFilter[T <: Pixel] extends Filter[Image[T]]
