package app.business.components.validators.file

import app.business.components.validators.file.specific.FileFormatValidator
import org.scalatest.FunSuite

class FileFormatValidatorTests extends FunSuite {
  val validator = new FileFormatValidator(Set("png", "jpg"))

  test("Empty supported formats set") {
    assertThrows[IllegalArgumentException](new FileFormatValidator(Set.empty))
    assertThrows[IllegalArgumentException](new FileFormatValidator(Set()))
  }

  test("Supported extension") {
    val path = "src/test/resources/images/meme.png"
    val validationResult = validator.validate(path)
    assert(!validationResult.hasErrors)
  }

  test("Unsupported extension") {
    val path = "src/test/resources/images/meme.abc"
    val validationResult = validator.validate(path)
    assert(validationResult.hasErrors)
  }

  test("Extension is supported, but file does not exist") {
    val path = "src/test/resources/images/file_does_not_exist.png"
    val validationResult = validator.validate(path)
    assert(!validationResult.hasErrors)
  }

  test("No extension") {
    val path = "src/test/resources/images/file"
    val validationResult = validator.validate(path)
    assert(validationResult.hasErrors)
  }

}
