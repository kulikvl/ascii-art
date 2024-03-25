package responsibilityChain

class SimpleHandler[T] extends Handler[T] {

  /**
   * Internally saved next handler.
   */
  protected var nextHandler: Option[Handler[T]] = None

  override def setNext(nextHandler: Handler[T]): Handler[T] = {
    this.nextHandler = Some(nextHandler)
    nextHandler
  }

  override def handle(item: T): Option[Handler[T]] = nextHandler

}

object SimpleHandler {

  def empty: SimpleHandler[Nothing] = new SimpleHandler[Nothing]

}
