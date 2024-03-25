package app.ui.console.views.handling.commands

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import org.mockito.MockitoSugar.{verify, verifyZeroInteractions}
import org.mockito.captor.ArgCaptor
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

abstract class CommandHandlerTests extends FunSuite {

  def getHandler(controller: ConsoleController): CommandHandler

  test("Invalid commands") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    // Process the invalid commands
    handler.handle(Seq("--help-pls"))
    handler.handle(Seq("--index-welcome"))
    handler.handle(Seq("--image-file", "path/to", "file.jpg"))
    handler.handle(Seq("--invalid-command a a a"))

    // Verify that the method was not called
    verifyZeroInteractions(mockController)
  }

}
