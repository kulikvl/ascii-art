package app.business.components.loaders.file.image.rgb

import app.business.components.loaders.file.image.rgb.specific.{JPGImageLoader, PNGImageLoader}
import org.scalatest.FunSuite

class RGBImageLoaderTests extends FunSuite {

  test("Get loader for png format") {
    val loaderOption = RGBImageLoader.getLoaderFor("png")

    loaderOption match {
      case Some(value) => assert(value.isInstanceOf[PNGImageLoader])
      case None => throw new AssertionError
    }
  }

  test("Get loader for jpg format") {
    val loaderOption = RGBImageLoader.getLoaderFor("jpg")

    loaderOption match {
      case Some(value) => assert(value.isInstanceOf[JPGImageLoader])
      case None => throw new AssertionError
    }
  }

  test("Get loader for unsupported format") {
    val loaderOption = RGBImageLoader.getLoaderFor("someWeirdFormat")

    assert(loaderOption === None)
  }

}
