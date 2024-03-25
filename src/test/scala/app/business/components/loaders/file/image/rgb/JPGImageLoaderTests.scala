package app.business.components.loaders.file.image.rgb

import app.business.components.loaders.file.image.rgb.specific.JPGImageLoader
import helpers.TestWithFiles
import org.scalatest.FunSuite
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class JPGImageLoaderTests extends FunSuite with TestWithFiles {
  val imageLoader = new JPGImageLoader

  test("Load bad source - Image does not exist") {
    assertThrows[IllegalArgumentException](imageLoader.load(""))
    assertThrows[IllegalArgumentException](imageLoader.load("abc/abc"))
    assertThrows[IllegalArgumentException](imageLoader.load("abc/abc.jpg"))
    assertThrows[IllegalArgumentException](imageLoader.load("img.jpg"))
  }

  test("Load bad source - illegal format") {
    val fileName = getTestFile("png")

    try {
      new File(fileName)
      assertThrows[IllegalArgumentException](imageLoader.load(fileName))
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Load 4 pixels image") {
    val fileName = getTestFile("jpg")

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
      ImageIO.write(image, "jpg", file)

      // Now test it
      val myImage = imageLoader.load(fileName)

      // We can't check pixel colors because of JPG compression algorithm (it will be yellowy)

      assert(myImage.pointGrid.width === 2)
      assert(myImage.pointGrid.height === 2)
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Load empty image") {
    val fileName = getTestFile("jpg")

    try {
      new File(fileName)
      assertThrows[IllegalArgumentException](imageLoader.load(fileName))
    }
    finally {
      ensureDeleted(fileName)
    }
  }
}

