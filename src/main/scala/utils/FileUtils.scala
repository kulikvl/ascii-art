package utils

object FileUtils {

  /**
   * Extracts the file extension from the given file path.
   *
   * @param fileName The file name or path to extract the extension from.
   * @return An Option containing the file extension if it exists, None otherwise.
   */
   def getFileExtension(fileName: String): Option[String] = {
    val lastDotIndex = fileName.lastIndexOf('.')

    if (lastDotIndex > 0 && lastDotIndex < fileName.length - 1 && fileName(lastDotIndex + 1) != '/') {
      Some(fileName.substring(lastDotIndex + 1).toLowerCase)
    } else {
      None
    }
  }

}
