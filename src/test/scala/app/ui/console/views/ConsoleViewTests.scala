package app.ui.console.views

import app.ui.console.controllers.ConsoleController
import org.scalatest.FunSuite
import org.mockito.Mockito.doNothing
import org.mockito.MockitoSugar.{times, verify, verifyZeroInteractions, when}
import org.mockito.captor.ArgCaptor
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar.mock


class ConsoleViewTests extends FunSuite {

  def makeView(controller: ConsoleController): ConsoleView = new ConsoleView(controller, ConsoleView.commandHandlers(controller))

  test("Run with empty arguments") {
    // Create view with a mock controller
    val mockController = mock[ConsoleController]
    val view = makeView(mockController)

    // Run with the cli commands
    view.run(Seq.empty)

    // Verify interaction with the controller
    verify(mockController).showIndex()
  }

  test("Run help") {
    // Create view with a mock controller
    val mockController = mock[ConsoleController]
    val view = makeView(mockController)

    // Run with the cli commands
    view.run(Seq("--help"))

    // Verify interaction with the controller
    verify(mockController).showHelp()
  }

  test("Run index") {
    // Create view with a mock controller
    val mockController = mock[ConsoleController]
    val view = makeView(mockController)

    // Run with the cli commands
    view.run(Seq("--index"))

    // Verify interaction with the controller
    verify(mockController).showIndex()
  }

  test("Run unknown command") {
    // Create view with a mock controller
    val mockController = mock[ConsoleController]
    val view = makeView(mockController)

    val errorMessageCapture = ArgCaptor[String]

    // Run with the cli commands
    view.run(Seq("--unknown-command"))

    // Verify interaction with the controller
    verify(mockController).showError(errorMessageCapture)
  }

  test("Run command with wrong syntax") {
    // Create view with a mock controller
    val mockController = mock[ConsoleController]
    val view = makeView(mockController)

    val errorMessageCapture = ArgCaptor[String]

    // Run with the cli commands
    view.run(Seq("--image", "path/to", "abc"))

    // Verify interaction with the controller
    verify(mockController).showError(errorMessageCapture)
  }

  test("Run complex command") {
    // Create view with a mock controller
    val mockController = mock[ConsoleController]
    val view = makeView(mockController)

    val imagePathCapture = ArgCaptor[String]
    val filePathCapture = ArgCaptor[String]

    // Run with the cli commands
    view.run(Seq("--image", "path/to/image.png", "--output-file", "path/to/file.txt"))

    // Verify interaction with the controller
    verify(mockController).addLoadImageJob(imagePathCapture)
    verify(mockController).addOutputFileJob(filePathCapture)

    // Verify parameters
    assert(imagePathCapture.value === "path/to/image.png")
    assert(filePathCapture.value === "path/to/file.txt")
  }

}
