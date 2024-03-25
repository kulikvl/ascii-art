package app.ui.console.views.handling.commands

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import org.mockito.MockitoSugar.verify
import org.scalatestplus.mockito.MockitoSugar.mock

class HelpCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new HelpCommandHandler(controller)

  test("Help command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    // Process a command
    handler.handle(Seq("--help"))

    // Verify that the method was called once
    verify(mockController).showHelp()
  }

}
