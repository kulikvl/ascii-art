package app.ui.console.views.handling.commands.`export`

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.CommandHandlerTests
import org.mockito.MockitoSugar.verify
import org.mockito.captor.ArgCaptor
import org.scalatestplus.mockito.MockitoSugar.mock

class OutputFileCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new OutputFileCommandHandler(controller)

  test("Output file command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    val filePathCapture = ArgCaptor[String]

    // Process a command
    handler.handle(Seq("--output-file", "path/to/file.txt"))

    // Verify that the method was called once
    verify(mockController).addOutputFileJob(filePathCapture)

    // Verify parameters
    assert(filePathCapture.value === "path/to/file.txt")
  }

}
