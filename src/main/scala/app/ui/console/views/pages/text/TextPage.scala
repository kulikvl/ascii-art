package app.ui.console.views.pages.text

import app.ui.console.views.pages.Page

trait TextPage extends Page[String] {

  /**
   * Renders a text page as string.
   */
  override def render(): String

}
