package app.ui.console.views.handling.commands.convert

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.CommandHandlerTests
import org.mockito.MockitoSugar.verify
import org.mockito.captor.ArgCaptor
import org.scalatestplus.mockito.MockitoSugar.mock

class PredefinedTableConvertCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new PredefinedTableConvertCommandHandler(controller)

  test("Predefined table command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    val predefinedTableCapture = ArgCaptor[String]

    // Process a command
    handler.handle(Seq("--table", "bourke-small"))

    // Verify that the method was called once
    verify(mockController).addPredefinedTableConvertJob(predefinedTableCapture)

    // Verify parameters
    assert(predefinedTableCapture.value === "bourke-small")
  }

}
