package app.business.components.converters.cli

import org.scalatest.FunSuite

class ArgsToCmdsTests extends FunSuite {
  val converter = new ArgsToCmds

  test("One command without arguments") {
    val res = converter.convert(Seq("--help"))
    assert(res === Seq(Seq("--help")))
  }

  test("Multiple commands without arguments") {
    val res = converter.convert(Seq("--help", "--index"))
    assert(res === Seq(Seq("--help"), Seq("--index")))
  }

  test("Empty sequence") {
    val res = converter.convert(Seq.empty)
    assert(res === Seq.empty)
  }

  test("Invalid syntax 1") {
    val res = converter.convert(Seq("-help"))
    assert(res === Seq.empty)
  }

  test("Invalid syntax 2") {
    val res = converter.convert(Seq("-help--please"))
    assert(res === Seq.empty)
  }

  test("Invalid multiple commands syntax") {
    val res = converter.convert(Seq("-help -index -continue--please -or-what"))
    assert(res === Seq.empty)
  }

  test("Command with argument") {
    val res = converter.convert(Seq("--output-console", "path/to/file.txt"))
    assert(res === Seq(Seq("--output-console", "path/to/file.txt")))
  }

  test("Command with multiple arguments") {
    val res = converter.convert(Seq("--a", "b", "c", "d"))
    assert(res === Seq(Seq("--a", "b", "c", "d")))
  }

  test("Multiple commands with multiple arguments 1") {
    val res = converter.convert(Seq("--a", "b", "c", "--d", "--e", "f"))
    assert(res === Seq(Seq("--a", "b", "c"), Seq("--d"), Seq("--e", "f")))
  }

  test("Multiple commands with multiple arguments 2") {
    val res = converter.convert(Seq("--help", "--image", "path/to/image.jpg", "--output-file", "file.txt"))
    assert(res === Seq(Seq("--help"), Seq("--image", "path/to/image.jpg"), Seq("--output-file", "file.txt")))
  }

}
