package app.ui.console.views.handling.commands.filter

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.CommandHandlerTests
import org.mockito.MockitoSugar.verify
import org.mockito.captor.ArgCaptor
import org.scalatestplus.mockito.MockitoSugar.mock

class FilterScaleCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new FilterScaleCommandHandler(controller)

  test("Scale filter command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    val scaleFactorCapture = ArgCaptor[Double]

    // Process a command
    handler.handle(Seq("--scale", "2.0"))

    // Verify that the method was called once
    verify(mockController).addFilterScaleJob(scaleFactorCapture)

    // Verify parameters
    assert(scaleFactorCapture.value === 2.0)
  }

}
