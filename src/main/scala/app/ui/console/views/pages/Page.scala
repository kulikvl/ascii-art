package app.ui.console.views.pages

trait Page[T] {

  /**
   * Renders (produces a representation) the pages content and returns it as type T.
   */
  def render(): T

}
