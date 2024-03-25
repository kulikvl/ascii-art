package utils

import org.scalatest.FunSuite

class FileUtilsTests extends FunSuite {

  test("No extension") {
    assert(FileUtils.getFileExtension("file") === None)
    assert(FileUtils.getFileExtension("path/to/file") === None)
    assert(FileUtils.getFileExtension("./src/path/to/file") === None)
    assert(FileUtils.getFileExtension("../src/path/to/file") === None)
    assert(FileUtils.getFileExtension("../.././src/path/to/file") === None)
  }

  test("With extension") {
    assert(FileUtils.getFileExtension("file.txt") === Some("txt"))
    assert(FileUtils.getFileExtension("path/to/file.omg") === Some("omg"))
    assert(FileUtils.getFileExtension(".././.././src/path/to/file.ooo") === Some("ooo"))
    assert(FileUtils.getFileExtension(".././.././src/path/to/file.ooo.ok") === Some("ok"))
  }
}
