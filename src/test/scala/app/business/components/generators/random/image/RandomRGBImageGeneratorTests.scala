package app.business.components.generators.random.image

import app.business.components.generators.random.image.specific.RandomRGBImageGenerator
import app.business.components.validators.file.specific.FilePathValidator
import helpers.TestWithFiles
import org.scalatest.FunSuite
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class RandomRGBImageGeneratorTests extends FunSuite with TestWithFiles {

  test("Illegal circles count") {
    assertThrows[IllegalArgumentException](new RandomRGBImageGenerator(55))
  }

  test("Illegal radius") {
    assertThrows[IllegalArgumentException](new RandomRGBImageGenerator(30, 3))
  }

  test("Save to file - Unsupported format") {
    val fileName = getTestFile("weirdFormat")

    try {
      val file = new File(fileName)
      val generator = new RandomRGBImageGenerator(exportFile = Some(file))

      assertThrows[IllegalArgumentException](generator.generate())
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Save to file - No format") {
    val fileName = getTestFile("")

    try {
      val file = new File(fileName)
      val generator = new RandomRGBImageGenerator(exportFile = Some(file))

      assertThrows[IllegalArgumentException](generator.generate())
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Save to file - No file exists") {
    val fileName = getTestFile("png")

    try {
      ensureDeleted(fileName)

      val file = new File(fileName)
      val generator = new RandomRGBImageGenerator(exportFile = Some(file))
      generator.generate()

      val filePathValRes = new FilePathValidator().validate(fileName)
      assert(!filePathValRes.hasErrors)

      val image: BufferedImage = ImageIO.read(new File(fileName))
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("Save to file - File already exists") {
    val fileName = getTestFile("png")

    try {
      ensureCreated(fileName)

      val file = new File(fileName)
      val generator = new RandomRGBImageGenerator(exportFile = Some(file))
      generator.generate()

      val filePathValRes = new FilePathValidator().validate(fileName)
      assert(!filePathValRes.hasErrors)

      val image: BufferedImage = ImageIO.read(new File(fileName))
    }
    finally {
      ensureDeleted(fileName)
    }
  }

}
