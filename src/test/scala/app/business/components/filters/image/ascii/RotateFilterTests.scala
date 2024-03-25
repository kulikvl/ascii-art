package app.business.components.filters

import app.business.components.filters.image.ascii.specific.RotateFilter
import app.business.models.pixel.specific.ASCIIPixel
import app.business.models.{Image, PointGrid}
import org.scalatest.FunSuite

class RotateFilterTests extends FunSuite {

  test("Incorrect angle") {
    assertThrows[IllegalArgumentException](new RotateFilter(-3))
    assertThrows[IllegalArgumentException](new RotateFilter(15))
    assertThrows[IllegalArgumentException](new RotateFilter(361))
  }

  test("1x1 image, identity") {
    val imageToRotate = Image(PointGrid(List(
      List(ASCIIPixel('A')))))

    val expectedImage = imageToRotate

    assert(new RotateFilter(90).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(90 * 2).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(90 * 3).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(90 * 4).apply(imageToRotate) === expectedImage)
  }

  test("2x2 image, rotate +90 (or same angles)") {
    val imageToRotate = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('C'), ASCIIPixel('A')),
      List(ASCIIPixel('D'), ASCIIPixel('B')))))

    assert(new RotateFilter(90).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(360 + 90).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(2 * 360 + 90).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(-270).apply(imageToRotate) === expectedImage)
  }

  test("2x2 image, rotate 180 (or same angles)") {
    val imageToRotate = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('D'), ASCIIPixel('C')),
      List(ASCIIPixel('B'), ASCIIPixel('A')))))

    assert(new RotateFilter(180).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(180 * 5).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(-180).apply(imageToRotate) === expectedImage)
  }

  test("2x2 image, rotate -90 (or same angles)") {
    val imageToRotate = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('B'), ASCIIPixel('D')),
      List(ASCIIPixel('A'), ASCIIPixel('C')))))

    assert(new RotateFilter(-90).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(270).apply(imageToRotate) === expectedImage)
    assert(new RotateFilter(-90 + 360 * 15).apply(imageToRotate) === expectedImage)
  }

  test("2x3 image, rotate 90") {
    val imageToRotate = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')),
      List(ASCIIPixel('E'), ASCIIPixel('F')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('E'), ASCIIPixel('C'), ASCIIPixel('A')),
      List(ASCIIPixel('F'), ASCIIPixel('D'), ASCIIPixel('B')))))

    assert(new RotateFilter(90).apply(imageToRotate) === expectedImage)
  }

  test("2x3 image, rotate 180") {
    val imageToRotate = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')),
      List(ASCIIPixel('E'), ASCIIPixel('F')))))

    val expectedImage = Image(PointGrid(List(
      List(ASCIIPixel('F'), ASCIIPixel('E')),
      List(ASCIIPixel('D'), ASCIIPixel('C')),
      List(ASCIIPixel('B'), ASCIIPixel('A')))))

    assert(new RotateFilter(180).apply(imageToRotate) === expectedImage)
  }

}
