package app.ui.console.views.pages.text

import app.ui.console.views.pages.text.specific.{ErrorPage, HelpPage}
import org.scalatest.FunSuite

class HelpPageTests extends FunSuite {

  test("Help page") {
    val result = new StringBuilder

    result.append(
      """|----- ASCII ART GENERATOR HELP PAGE -----
         |
         |DESCRIPTION
         |  --image source
         |      load image from the source
         |  --image-random
         |      generate random image instead of loading
         |
         |  --table name
         |      specify ascii predefined conversion table
         |  --custom-table table
         |      specify custom ascii conversion table
         |
         |  --invert
         |      invert the ascii image
         |  --rotate angle
         |      rotate the ascii image
         |  --scale value
         |      scale the ascii image
         |
         |  --output-file target
         |      saves the result ascii image to the file
         |  --output-console
         |      outputs the result ascii image to the console
         |
         |IMPORTANT
         |  Supported image formats: jpg, png
         |
         |EXAMPLES
         |  run --image test-image.jpg [filter 1] [filter 2] [filter 3] --output-console
         |
         |-----------------------------------------
         |""".stripMargin)

    val expectedString = result.toString()

    val page = new HelpPage
    assert(page.render() === expectedString)
  }

}
