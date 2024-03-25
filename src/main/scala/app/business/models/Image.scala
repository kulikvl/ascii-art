package app.business.models

import app.business.models.pixel.Pixel

case class Image[+T <: Pixel](pointGrid: PointGrid [T]) {

  /**
   * Transforms image of type T to type A.
   *
   * @param mapper Mapper function to be applied.
   */
  def transform[A <: Pixel] (mapper: T => A): Image [A] = {
    Image(pointGrid.map(mapper))
  }

}
