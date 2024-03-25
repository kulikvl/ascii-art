package app.business.components.converters.pixel.pixelToPixel.rgbToGreyscale

import app.business.components.converters.pixel.pixelToPixel.rgbToGreyscale.specific.RGBPixelToGreyscalePixelLuminosity
import app.business.models.pixel.specific.{GreyscalePixel, RGBPixel}
import org.scalatest.FunSuite

class RGBPixelToGreyscalePixelLuminosityTests extends FunSuite {
  val converter = new RGBPixelToGreyscalePixelLuminosity

  test("Edge rgb pixels") {
    assert(converter.convert(RGBPixel(0, 0, 0)) === GreyscalePixel(0))
    assert(converter.convert(RGBPixel(255, 255, 255)) === GreyscalePixel(255))
  }

  test("Median rgb pixels") {
    assert(converter.convert(RGBPixel(10, 200, 6)) === GreyscalePixel(122))
    assert(converter.convert(RGBPixel(62, 50, 127)) === GreyscalePixel(62))
  }

}
