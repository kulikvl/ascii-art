package app.business.models.pixel

import app.business.models.pixel.specific.RGBPixel
import org.scalatest.FunSuite

class RGBPixelTests extends FunSuite {

  test("Valid") {
    RGBPixel(0, 155, 255)
  }

  test("Invalid") {
    assertThrows[IllegalArgumentException](RGBPixel(-1, 100, 0))
    assertThrows[IllegalArgumentException](RGBPixel(-0, 100, 256))
  }

}
