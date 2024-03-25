package app.business.models.pixel.specific

import app.business.models.pixel.Pixel

case class GreyscalePixel(intensity: Int) extends Pixel {
  require(intensity >= 0 && intensity <= 255, "Greyscale intensity must be between 0 and 255")
}
