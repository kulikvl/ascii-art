package app.business.models.pixel.specific

import app.business.models.pixel.Pixel

case class ASCIIPixel(symbol: Char) extends Pixel {
  require(symbol.toInt >= 0 && symbol.toInt <= 127, "Symbol must be a valid ASCII character")
}
