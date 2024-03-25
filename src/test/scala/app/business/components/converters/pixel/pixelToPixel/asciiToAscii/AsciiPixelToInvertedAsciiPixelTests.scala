package app.business.components.converters.pixel.pixelToPixel.asciiToAscii

import app.business.components.converters.pixel.pixelToPixel.asciiToAscii.specific.AsciiPixelToInvertedAsciiPixel
import app.business.models.pixel.specific.ASCIIPixel
import org.scalatest.FunSuite

class AsciiPixelToInvertedAsciiPixelTests extends FunSuite {

  test("Empty ramp") {
    assertThrows[IllegalArgumentException](new AsciiPixelToInvertedAsciiPixel(""))
  }

  test("1 symbol ramp") {
    val converter = new AsciiPixelToInvertedAsciiPixel("a")
    assert(converter.convert(ASCIIPixel('a')) === ASCIIPixel('a'))
  }

  test("Character is not in the ramp") {
    val converter = new AsciiPixelToInvertedAsciiPixel("abcd")
    assertThrows[IllegalArgumentException](converter.convert(ASCIIPixel('e')))
  }

  test("4 symbols ramp") {
    val converter = new AsciiPixelToInvertedAsciiPixel("abcd")
    assert(converter.convert(ASCIIPixel('a')) === ASCIIPixel('d'))
    assert(converter.convert(ASCIIPixel('b')) === ASCIIPixel('c'))
    assert(converter.convert(ASCIIPixel('c')) === ASCIIPixel('b'))
  }

  test("7 symbols ramp") {
    val converter = new AsciiPixelToInvertedAsciiPixel("abcdefg")
    assert(converter.convert(ASCIIPixel('a')) === ASCIIPixel('g'))
    assert(converter.convert(ASCIIPixel('c')) === ASCIIPixel('e'))
    assert(converter.convert(ASCIIPixel('d')) === ASCIIPixel('d'))
    assert(converter.convert(ASCIIPixel('e')) === ASCIIPixel('c'))
  }

}
