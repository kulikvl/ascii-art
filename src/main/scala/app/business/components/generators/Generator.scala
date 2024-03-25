package app.business.components.generators

/**
 * Represents an entity capable of generating something of type T.
 *
 * @tparam T type of the generated item.
 */
trait Generator[T] {

  def generate(): T

}
