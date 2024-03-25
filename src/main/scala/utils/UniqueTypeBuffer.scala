package utils

import scala.collection.mutable

/**
 * Buffer that can only contain objects of the unique types.
 */
class UniqueTypeBuffer[T] {
  private val objects: mutable.Buffer[T] = mutable.Buffer.empty

  /**
   * Adds the new object.
   * If the type of the new object already exists, it rewrites the old object.
   */
  def add(newObj: T): UniqueTypeBuffer[T] = {
    val objOption = objects.find(o => o.getClass == newObj.getClass)

    objOption match {
      case Some(oldObj) => objects -= oldObj
      case None =>
    }

    objects += newObj

    this
  }

  def toSeq: Seq[T] = objects.toSeq
}
