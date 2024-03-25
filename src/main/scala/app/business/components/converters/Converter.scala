package app.business.components.converters

/**
 * Generic interface for components that convert data of type T to another type B.
 *
 * @tparam T the input type to convert from.
 * @tparam B the export type to convert to.
 */
trait Converter [T,B] {

  /**
   * Converts the input item of type T to the export item of type B.
   *
   * @param item Item to be converted.
   */
  def convert(item: T): B

}
