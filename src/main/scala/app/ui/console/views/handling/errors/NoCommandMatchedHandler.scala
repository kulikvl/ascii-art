package app.ui.console.views.handling.errors

import responsibilityChain.{Handler, SimpleHandler}

class NoCommandMatchedHandler extends SimpleHandler[Seq[String]] {

  override def handle(command: Seq[String]): Option[Handler[Seq[String]]] =
    throw new IllegalArgumentException(s"No such command: ${command.head}")

}

