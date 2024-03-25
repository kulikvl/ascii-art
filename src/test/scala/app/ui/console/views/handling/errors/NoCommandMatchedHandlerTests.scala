package app.ui.console.views.handling.errors

import org.scalatest.FunSuite

class NoCommandMatchedHandlerTests extends FunSuite {

  test("No command matched") {
    val handler = new NoCommandMatchedHandler

    assertThrows[IllegalArgumentException](handler.handle(Seq("some command")))
  }

}
