package app.business.models

import app.business.models.pixel.specific.{ASCIIPixel, GreyscalePixel}
import org.scalatest.FunSuite

class ImageTests extends FunSuite {

  test("transform") {
    val image = Image(PointGrid(List(
      List(ASCIIPixel('A'), ASCIIPixel('B')),
      List(ASCIIPixel('C'), ASCIIPixel('D')))))

    def mapper(asciiPixel: ASCIIPixel): GreyscalePixel = GreyscalePixel(asciiPixel.symbol.toInt + 3 % 256)

    val imageExpected = Image(PointGrid(List(
      List(GreyscalePixel(68), GreyscalePixel(69)),
      List(GreyscalePixel(70), GreyscalePixel(71)))))

    assert(image.transform(mapper) === imageExpected)
  }

}
