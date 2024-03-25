package app.ui.console.views.pages.text.specific

import app.ui.console.views.pages.text.TextPage

class ErrorPage(errorMessage: String) extends TextPage {

  override def render(): String = s"Oops, something went wrong:\n  $errorMessage\n"

}
