package app.business.models.pixel

import app.business.models.pixel.specific.ASCIIPixel
import org.scalatest.FunSuite

class ASCIIPixelTests extends FunSuite {

  test("Valid") {
    ASCIIPixel('A')
    ASCIIPixel('#')
  }

  test("Invalid") {
    assertThrows[IllegalArgumentException](ASCIIPixel('中'))
    assertThrows[IllegalArgumentException](ASCIIPixel('€'))
  }

}
