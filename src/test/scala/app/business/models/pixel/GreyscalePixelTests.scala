package app.business.models.pixel

import app.business.models.pixel.specific.GreyscalePixel
import org.scalatest.FunSuite

class GreyscalePixelTests extends FunSuite {

  test("Valid") {
    GreyscalePixel(123)
    GreyscalePixel(0)
  }

  test("Invalid") {
    assertThrows[IllegalArgumentException](GreyscalePixel(256))
    assertThrows[IllegalArgumentException](GreyscalePixel(-1))
  }

}
