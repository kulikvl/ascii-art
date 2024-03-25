package app.business.components.converters.image.imageToString

import app.business.components.converters.image.imageToString.specific.AsciiImageToString
import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}
import org.scalatest.FunSuite

class AsciiImageToStringTests extends FunSuite {
  val converter = new AsciiImageToString

  test("1x1 pixels image") {
    val img = Image(PointGrid(List(
      List(ASCIIPixel('A')))))

    assert(converter.convert(img) === "A\n")
  }

  test("2x3 pixels image") {
    val img = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')),
      List(ASCIIPixel('/'), ASCIIPixel('/')))))

    assert(converter.convert(img) === "AB\nCD\n//\n")
  }

  test("1x3 pixels image") {
    val img = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B'), ASCIIPixel('C')))))

    assert(converter.convert(img) === "ABC\n")
  }

}
