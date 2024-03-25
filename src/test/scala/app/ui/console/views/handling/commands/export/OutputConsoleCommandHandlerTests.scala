package app.ui.console.views.handling.commands.`export`

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.CommandHandlerTests
import org.mockito.MockitoSugar.verify
import org.mockito.captor.ArgCaptor
import org.scalatestplus.mockito.MockitoSugar.mock

class OutputConsoleCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new OutputConsoleCommandHandler(controller)

  test("Output console command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    // Process a command
    handler.handle(Seq("--output-console"))

    // Verify that the method was called once
    verify(mockController).addOutputConsoleJob()
  }

}
