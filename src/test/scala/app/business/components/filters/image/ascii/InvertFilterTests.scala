package app.business.components.filters

import app.business.components.filters.image.ascii.specific.InvertFilter
import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}
import org.scalatest.FunSuite

class InvertFilterTests extends FunSuite {

  test("Empty ramp") {
    assertThrows[IllegalArgumentException](new InvertFilter(""))
  }

  test("Character is not from ramp") {
    val filter = new InvertFilter("abcdef")

    val imageToInvert = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('f')),
      List(ASCIIPixel('b'), ASCIIPixel('e')),
      List(ASCIIPixel('c'), ASCIIPixel('d')))))

    assertThrows[IllegalArgumentException](filter.apply(imageToInvert))
  }

  test("2x3 image invert") {
    val filter = new InvertFilter("abcdef")

    val imageToInvert = Image(PointGrid(List(
      List(ASCIIPixel('a'), ASCIIPixel('f')),
      List(ASCIIPixel('b'), ASCIIPixel('e')),
      List(ASCIIPixel('c'), ASCIIPixel('d')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('f'), ASCIIPixel('a')),
      List(ASCIIPixel('e'), ASCIIPixel('b')),
      List(ASCIIPixel('d'), ASCIIPixel('c')))))

    assert(filter.apply(imageToInvert) === expectedImage)
  }

  test("Identity invert") {
    val filter = new InvertFilter("aaaaa")

    val imageToInvert = Image(PointGrid(List(
      List(ASCIIPixel('a'), ASCIIPixel('a')),
      List(ASCIIPixel('a'), ASCIIPixel('a')),
      List(ASCIIPixel('a'), ASCIIPixel('a')))))

    val expectedImage = imageToInvert

    assert(filter.apply(imageToInvert) === expectedImage)
  }

}
