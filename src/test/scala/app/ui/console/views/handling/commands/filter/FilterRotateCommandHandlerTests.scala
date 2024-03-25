package app.ui.console.views.handling.commands.filter

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.CommandHandlerTests
import org.mockito.MockitoSugar.verify
import org.mockito.captor.ArgCaptor
import org.scalatestplus.mockito.MockitoSugar.mock

class FilterRotateCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new FilterRotateCommandHandler(controller)

  test("Rotate filter command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    val rotateAngleCapture = ArgCaptor[Int]

    // Process a command
    handler.handle(Seq("--rotate", "90"))

    // Verify that the method was called once
    verify(mockController).addFilterRotateJob(rotateAngleCapture)

    // Verify parameters
    assert(rotateAngleCapture.value === 90)
  }

}
