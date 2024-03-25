package app.business.components.loaders

/**
 * Generic interface for components that load data from various sources.
 *
 * @tparam T Type of the source from which data will be loaded.
 * @tparam B Type of the result produced after loading the data.
 */
trait Loader[T, B] {

  /**
   * Loads data from the specified source and returns it in the form of type B.
   *
   * @param source The source from which data is to be loaded.
   * @return The data loaded from the source, as type B.
   */
  def load(source: T): B

}
