package app.business.components.validators.file

import app.business.components.validators.file.specific.FilePathValidator
import helpers.TestWithFiles
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class FilePathValidatorTests extends FunSuite with BeforeAndAfterAll with TestWithFiles {
  val validator = new FilePathValidator
  val fileName: String = getTestFile("png")

  override def beforeAll(): Unit = {
    ensureCreated(fileName)
  }

  test("File exists") {
    val validationResult = validator.validate(fileName)
    assert(!validationResult.hasErrors)
  }

  test("File does not exist") {
    val path = "src/test/resources/images/does_not_exist.png"
    val validationResult = validator.validate(path)
    assert(validationResult.hasErrors)
  }

  test("Exists, but directory") {
    val path = "src/test/resources"
    val validationResult = validator.validate(path)
    assert(validationResult.hasErrors)
  }

  override def afterAll(): Unit = {
    ensureDeleted(fileName)
  }

}
