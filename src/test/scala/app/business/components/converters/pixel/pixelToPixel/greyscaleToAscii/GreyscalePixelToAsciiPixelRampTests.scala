package app.business.components.converters.pixel.pixelToPixel.greyscaleToAscii

import app.business.components.converters.pixel.pixelToPixel.greyscaleToAscii.specific.GreyscalePixelToAsciiPixelRamp
import app.business.models.pixel.specific.{ASCIIPixel, GreyscalePixel}
import org.scalatest.FunSuite

class GreyscalePixelToAsciiPixelRampTests extends FunSuite {

  test("Empty ramp") {
    assertThrows[IllegalArgumentException](new GreyscalePixelToAsciiPixelRamp(""))
  }

  test("1 symbol ramp") {
    val converter = new GreyscalePixelToAsciiPixelRamp("a")

    assert(converter.convert(GreyscalePixel(123)) === ASCIIPixel('a'))
    assert(converter.convert(GreyscalePixel(255)) === ASCIIPixel('a'))
    assert(converter.convert(GreyscalePixel(0)) === ASCIIPixel('a'))
  }

  test("3 symbols ramp") {
    val converter = new GreyscalePixelToAsciiPixelRamp("abc")

    assert(converter.convert(GreyscalePixel(255)) === ASCIIPixel('c'))
    assert(converter.convert(GreyscalePixel(230)) === ASCIIPixel('c'))
    assert(converter.convert(GreyscalePixel(140)) === ASCIIPixel('b'))
    assert(converter.convert(GreyscalePixel(110)) === ASCIIPixel('b'))
    assert(converter.convert(GreyscalePixel(0)) === ASCIIPixel('a'))
    assert(converter.convert(GreyscalePixel(50)) === ASCIIPixel('a'))
  }

}
