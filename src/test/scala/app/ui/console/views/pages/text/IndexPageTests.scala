package app.ui.console.views.pages.text

import app.ui.console.views.pages.text.specific.{HelpPage, IndexPage}
import org.scalatest.FunSuite

class IndexPageTests extends FunSuite {

  test("Index page") {
    val result = new StringBuilder

    result.append(
      """|----- ASCII ART GENERATOR INDEX PAGE -----
         |
         |Welcome! If you want to see the documentation with all the commands, rerun the application with flag --help
         |
         |------------------------------------------
         |""".stripMargin)

    val expectedString = result.toString()

    val page = new IndexPage
    assert(page.render() === expectedString)
  }

}
