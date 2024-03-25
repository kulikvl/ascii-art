package app.business.models

/**
 * Represents a non-empty (at least 1 point) two-dimensional grid composed of points.
 *
 * @param points 2D sequence of points.
 * @tparam T Type of the point.
 */
case class PointGrid[+T] (points: Seq[Seq[T]]) {
  require(points.nonEmpty && points.head.nonEmpty && points.forall(_.length == points.head.length), "Given points do not form a 2D grid")

  /**
   * Returns a point of type T from the grid based on provided coordinates.
   *
   * @param x X coordinate of the point.
   * @param y Y coordinate of the point.
   */
  def pointAt(x: Int, y: Int): T = {
    if (x < 0 || y < 0 || x > width || y > height) throw new IllegalArgumentException("Given point coordinates are out of bounds")
    points.slice(y, y + 1).head.slice(x, x + 1).head
  }

  /**
   * Returns the height of the grid.
   */
  def height: Int = points.size

  /**
   * Returns the width of the grid.
   */
  def width: Int = points.head.size

  /**
   * Returns a line from the grid based on the provided y-coordinate.
   *
   * @param y Y coordinate of the line.
   */
  def lineAt(y: Int): Seq[T] = {
    if (y < 0 || y >= height) throw new IllegalArgumentException("Given y-coordinate is out of bounds")
    points.slice(y, y + 1).head
  }

  /**
   * Applies a mapper function to the grid.
   *
   * @param mapper Mapper function to be applied.
   * @return New grid.
   */
  def map[A] (mapper: T => A): PointGrid[A] = {
    PointGrid(points.map(_.map(mapper)))
  }

}