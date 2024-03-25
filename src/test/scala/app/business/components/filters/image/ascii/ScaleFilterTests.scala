package app.business.components.filters

import app.business.components.filters.image.ascii.specific.{InvertFilter, ScaleFilter}
import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}
import org.scalatest.FunSuite

class ScaleFilterTests extends FunSuite {

  test("Illegal scale") {
    assertThrows[IllegalArgumentException](new ScaleFilter(0))
    assertThrows[IllegalArgumentException](new ScaleFilter(5))
    assertThrows[IllegalArgumentException](new ScaleFilter(0.3))
  }

  test("1x1 image, scale 0.5") {
    val imageToScale = Image(PointGrid(List(
      List(ASCIIPixel('A')))))

    val expectedImage = imageToScale

    assert(new ScaleFilter(0.5).apply(imageToScale) === expectedImage)
  }

  test("1x1 image, scale 1") {
    val imageToScale = Image(PointGrid(List(
      List(ASCIIPixel('A')))))

    val expectedImage = imageToScale

    assert(new ScaleFilter(1).apply(imageToScale) === expectedImage)
  }

  test("1x1 image, scale 2") {
    val imageToScale = Image(PointGrid(List(
      List(ASCIIPixel('A')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('A')),
      List(ASCIIPixel('A'), ASCIIPixel('A')))))

    assert(new ScaleFilter(2).apply(imageToScale) === expectedImage)
  }

  test("2x3 image, scale 0.5") {
    val imageToScale= Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')),
      List(ASCIIPixel('E'), ASCIIPixel('F')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('A')),
      List(ASCIIPixel('E')))))

    assert(new ScaleFilter(0.5).apply(imageToScale) === expectedImage)
  }

  test("2x3 image, scale 1") {
    val imageToScale = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')),
      List(ASCIIPixel('E'), ASCIIPixel('F')))))

    val expectedImage = imageToScale

    assert(new ScaleFilter(1).apply(imageToScale) === expectedImage)
  }

  test("2x3 image, scale 2.0") {
    val imageToScale = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')),
      List(ASCIIPixel('E'), ASCIIPixel('F')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('A'), ASCIIPixel('B'), ASCIIPixel('B')),
      List(ASCIIPixel('A'), ASCIIPixel('A'), ASCIIPixel('B'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('C'), ASCIIPixel('D'), ASCIIPixel('D')),
      List(ASCIIPixel('C'), ASCIIPixel('C'), ASCIIPixel('D'), ASCIIPixel('D')),
      List(ASCIIPixel('E'), ASCIIPixel('E'), ASCIIPixel('F'), ASCIIPixel('F')),
      List(ASCIIPixel('E'), ASCIIPixel('E'), ASCIIPixel('F'), ASCIIPixel('F')))))

    assert(new ScaleFilter(2).apply(imageToScale) === expectedImage)
  }


  test("3x3 image, scale 0.5") {
    val imageToScale = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B'), ASCIIPixel('C')),
      List(ASCIIPixel('D'), ASCIIPixel('E'), ASCIIPixel('F')),
      List(ASCIIPixel('G'), ASCIIPixel('H'), ASCIIPixel('I')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('C')),
      List(ASCIIPixel('G'), ASCIIPixel('I')))))

    assert(new ScaleFilter(0.5).apply(imageToScale) === expectedImage)
  }

  test("4x4 image, scale 0.5") {
    val imageToScale = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('X'), ASCIIPixel('C'), ASCIIPixel('X')),
      List(ASCIIPixel('X'), ASCIIPixel('X'), ASCIIPixel('X'), ASCIIPixel('X')),
      List(ASCIIPixel('B'), ASCIIPixel('X'), ASCIIPixel('D'), ASCIIPixel('X')),
      List(ASCIIPixel('X'), ASCIIPixel('X'), ASCIIPixel('X'), ASCIIPixel('X')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('C')),
      List(ASCIIPixel('B'), ASCIIPixel('D')))))

    assert(new ScaleFilter(0.5).apply(imageToScale) === expectedImage)
  }

}
