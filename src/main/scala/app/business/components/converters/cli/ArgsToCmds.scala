package app.business.components.converters.cli

import app.business.components.converters.Converter

/**
 * Class for converting raw command-line arguments into a sequence of complete commands (directives).
 *
 * Example of a command without arguments: --help
 * Example of a command with arguments: --output-file path/to/file
 *
 * For example, converts
 *  Seq("--help", "--image", "path/to/image.jpg", "--output-file", "file.txt")
 *  into
 *  Seq(Seq("--help"), Seq("--image", "path/to/image.jpg"), Seq("--output-file", "file.txt")).
 */
class ArgsToCmds extends Converter[Seq[String], Seq[Seq[String]]] {

  override def convert(args: Seq[String]): Seq[Seq[String]] = {
    if (!args.exists(s => s.startsWith("--"))) return Seq.empty

    val parsedArgs = args.foldLeft(Seq[Seq[String]]()) { (acc, arg) =>
      if (arg.startsWith("--")) acc :+ Seq(arg) // Start a new subsequence when encountering a command
      else acc.init :+ (acc.last :+ arg) // Otherwise, add the argument to the last subsequence
    }

    parsedArgs
  }

}
