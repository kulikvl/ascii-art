package app.ui.console.views.handling.commands.load

import app.ui.console.controllers.ConsoleController
import app.ui.console.views.handling.CommandHandler
import app.ui.console.views.handling.commands.{CommandHandlerTests, IndexCommandHandler}
import org.mockito.MockitoSugar.verify
import org.scalatestplus.mockito.MockitoSugar.mock
import org.mockito.captor.ArgCaptor

class LoadImageCommandHandlerTests extends CommandHandlerTests {
  override def getHandler(controller: ConsoleController): CommandHandler = new LoadImageCommandHandler(controller)

  test("Load image command") {
    // Create mocks
    val mockController = mock[ConsoleController]
    val handler = getHandler(mockController)

    val imagePathCapture = ArgCaptor[String]

    // Process a command
    handler.handle(Seq("--image", "path/to/image.png"))

    // Verify that the method was called once
    verify(mockController).addLoadImageJob(imagePathCapture)

    // Verify parameters
    assert(imagePathCapture.value === "path/to/image.png")
  }
}
