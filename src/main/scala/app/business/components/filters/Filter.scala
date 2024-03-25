package app.business.components.filters

/**
 * Generic interface for components that filter items.
 *
 * @tparam T Type of the item that will be filtered.
 */
trait Filter[T] {

  /**
   * Applies the filter on an item of type T and returns new item of the same type.
   * @param item Item to apply the filter on.
   */
  def apply(item: T): T

}
