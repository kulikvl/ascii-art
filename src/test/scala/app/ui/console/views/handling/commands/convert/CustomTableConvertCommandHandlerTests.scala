package app.ui.console.views.handling.commands.convert

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.CommandHandlerTests
import org.mockito.MockitoSugar.verify
import org.mockito.captor.ArgCaptor
import org.scalatestplus.mockito.MockitoSugar.mock

class CustomTableConvertCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new CustomTableConvertCommandHandler(controller)

  test("Custom table command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    val customTableCapture = ArgCaptor[String]

    // Process a command
    handler.handle(Seq("--custom-table", "abc"))

    // Verify that the method was called once
    verify(mockController).addCustomTableConvertJob(customTableCapture)

    // Verify parameters
    assert(customTableCapture.value === "abc")
  }

}
