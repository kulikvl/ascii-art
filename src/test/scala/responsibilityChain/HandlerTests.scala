package responsibilityChain

import org.scalatest.FunSuite

class HandlerTests extends FunSuite {

  class TestHandler extends SimpleHandler[String] {
    var callCount = 0

    override def handle(item: String): Option[Handler[String]] = {
      callCount += 1
      super.handle(item)
    }
  }

  test("resolveAll") {
    val handler1 = new TestHandler()
    val handler2 = new TestHandler()
    val handler3 = new TestHandler()

    handler1
      .setNext(handler2)
      .setNext(handler3)

    Handler.resolveAll(handler1, "random stuff")

    assert(handler1.callCount === 1)
    assert(handler2.callCount === 1)
    assert(handler3.callCount === 1)
  }
}
