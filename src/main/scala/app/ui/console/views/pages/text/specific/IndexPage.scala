package app.ui.console.views.pages.text.specific

import app.ui.console.views.pages.text.TextPage

class IndexPage extends TextPage {

  override def render(): String = {
    val result = new StringBuilder

    result.append(
      """|----- ASCII ART GENERATOR INDEX PAGE -----
         |
         |Welcome! If you want to see the documentation with all the commands, rerun the application with flag --help
         |
         |------------------------------------------
         |""".stripMargin)

    result.toString()
  }

}
