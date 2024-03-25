package app.business.components.validators.file.specific

import app.business.components.validators.file.FileValidator
import app.business.components.validators.ValidationResult

import java.nio.file.{Files, Paths}

/**
 * Class used to validate file path.
 */
class FilePathValidator extends FileValidator {

  /**
   * Checks that the file on the specified path exists and is actually a file (not directory).
   *
   * @param path Input file path to validate.
   * @return `ValidationResult` object.
   */
  override def validate(path: String): ValidationResult = {
    val p = Paths.get(path)

    val fileExists = Files.exists(p)
    val isDirectory = Files.isDirectory(p)

    ValidationResult(!fileExists || isDirectory, s"File exists: $fileExists, is directory: $isDirectory")
  }

}
