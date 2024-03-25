package app.business.components.validators

/**
 * Generic validator.
 *
 * @tparam T The type of the object that is to be validated.
 */
trait Validator[T] {

  /**
   * Validates an item of type T.
   *
   * @param item The item to be validated.
   * @return `ValidationResult` object.
   */
  def validate(item: T): ValidationResult

}
