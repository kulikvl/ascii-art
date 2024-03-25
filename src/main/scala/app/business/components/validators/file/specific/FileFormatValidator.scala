package app.business.components.validators.file.specific

import app.business.components.validators.file.FileValidator
import app.business.components.validators.ValidationResult
import utils.FileUtils.getFileExtension

import java.nio.file.Paths

/**
 * Class used to validate file extension.
 */
class FileFormatValidator(validExtensions: Set[String]) extends FileValidator {
  require(validExtensions.nonEmpty, "Valid extensions set cannot be empty")

  /**
   * Checks that the file has the allowed extension.
   *
   * @param path Input file path or file name to validate.
   * @return `ValidationResult` object.
   */
  override def validate(path: String): ValidationResult = {
    val p = Paths.get(path)

    val maybeExtension = getFileExtension(p.toString)

    maybeExtension match {
      case Some(e) => if (validExtensions.contains(e)) ValidationResult(hasErrors = false, "OK") else ValidationResult(hasErrors = true, s"Unsupported file extension: $e")
      case None => ValidationResult(hasErrors = true, s"There is no file extension: $path")
    }
  }

}
