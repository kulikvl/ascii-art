package app.ui.console.views.handling

import responsibilityChain.{Handler, SimpleHandler}

abstract class CommandHandler extends SimpleHandler[Seq[String]] {

  /**
   * Name of the command.
   */
  def commandName: String

  /**
   * Number of arguments the command takes.
   */
  def argsCount: Int

  /**
   * Method handling the command (= parsed cli-arguments that form a single job).
   *
   * @param cliCmd An input command. For example: Seq("--rotate", "+90").
   * @return An optional next handler.
   */
  override def handle(cliCmd: Seq[String]): Option[Handler[Seq[String]]] = cliCmd match {
    case Nil => None
    case head +: _ if !head.startsWith("--") => throw new IllegalArgumentException(s"Command $head does not start with --")
    case head +: _ if head != commandName => nextHandler
    case _ +: tail if tail.size != argsCount => throw new IllegalArgumentException(s"Incorrect amount of arguments for $commandName, expected $argsCount")
    case _ =>
      processCommand(cliCmd.tail.take(argsCount))
      None
  }

  /**
   * Processes arguments of the command.
   *
   * @param args Arguments to process. For example their type should be checked.
   */
  protected def processCommand(args: Seq[String]): Unit

}

