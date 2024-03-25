package app.business.components.loaders.file.image.rgb

import app.business.components.loaders.file.image.rgb.specific.PNGImageLoader
import app.business.models.pixel.specific.RGBPixel
import helpers.TestWithFiles
import org.scalatest.FunSuite
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class PNGImageLoaderTests extends FunSuite with TestWithFiles {
  val imageLoader = new PNGImageLoader

  test("Load bad source - Image does not exist") {
    assertThrows[IllegalArgumentException](imageLoader.load(""))
    assertThrows[IllegalArgumentException](imageLoader.load("abc/abc"))
    assertThrows[IllegalArgumentException](imageLoader.load("abc/abc.png"))
    assertThrows[IllegalArgumentException](imageLoader.load("img.png"))
  }

  test("Load bad source - illegal format") {
    val fileName = getTestFile("jpg")

    try {
      new File(fileName)
      assertThrows[IllegalArgumentException](imageLoader.load(fileName))
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Load 4 pixels image") {
    val fileName = getTestFile("png")

    try {
      // Create the image first
      val image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB)

      // Set the pixels
      image.setRGB(0, 0, 0xFF0000) // Red at top-left
      image.setRGB(1, 0, 0x00FF00) // Green at top-right
      image.setRGB(0, 1, 0x0000FF) // Blue at bottom-left
      image.setRGB(1, 1, 0xFFFF00) // Yellow at bottom-right

      // Write the image to a file
      val file = new File(fileName)
      ImageIO.write(image, "png", file)

      // Now test it
      val myImage = imageLoader.load(fileName)

      assert(myImage.pointGrid.pointAt(0, 0) === RGBPixel(255, 0, 0))
      assert(myImage.pointGrid.pointAt(1, 0) === RGBPixel(0, 255, 0))
      assert(myImage.pointGrid.pointAt(0, 1) === RGBPixel(0, 0, 255))
      assert(myImage.pointGrid.pointAt(1, 1) === RGBPixel(255, 255, 0))
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Load empty image") {
    val fileName = getTestFile("png")

    try {
      new File(fileName)
      assertThrows[IllegalArgumentException](imageLoader.load(fileName))
    }
    finally {
      ensureDeleted(fileName)
    }
  }
}
