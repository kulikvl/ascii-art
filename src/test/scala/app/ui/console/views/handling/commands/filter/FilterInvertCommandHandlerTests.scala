package app.ui.console.views.handling.commands.filter

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.CommandHandlerTests
import org.mockito.MockitoSugar.verify
import org.mockito.captor.ArgCaptor
import org.scalatestplus.mockito.MockitoSugar.mock

class FilterInvertCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new FilterInvertCommandHandler(controller)

  test("Invert filter command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    // Process a command
    handler.handle(Seq("--invert"))

    // Verify that the method was called once
    verify(mockController).addFilterInvertJob()
  }

}
