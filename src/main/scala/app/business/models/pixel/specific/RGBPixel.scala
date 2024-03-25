package app.business.models.pixel.specific

import app.business.models.pixel.Pixel

case class RGBPixel(r: Int, g: Int, b: Int) extends Pixel {
  require(Seq(r, g, b).forall(v => v >= 0 && v <= 255), "Color values must be between 0 and 255")
}