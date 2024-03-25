package exporters

/**
 * Generic interface for components that export items of type T to a specific destination.
 * This could be to a stream (console, a file), or a network, or any other medium.
 *
 * @tparam T Type of the item to be exported.
 */
trait Exporter[T] {

  /**
   * Exports the provided item to a designated destination.
   *
   * @param item The item of type T to be exported.
   */
  def export(item: T): Unit

}
