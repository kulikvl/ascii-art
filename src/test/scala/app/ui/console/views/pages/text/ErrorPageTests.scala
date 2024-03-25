package app.ui.console.views.pages.text

import app.ui.console.views.pages.text.specific.ErrorPage
import org.scalatest.FunSuite

class ErrorPageTests extends FunSuite {

  test("Error page") {
    val errMsg = "FATAL ERROR!"
    val page = new ErrorPage(errMsg)
    assert(page.render() === s"Oops, something went wrong:\n  $errMsg\n")
  }

}
