package app.business.models

import org.scalatest.FunSuite

class PointGridTests extends FunSuite {

  test("Empty grid") {
    assertThrows[IllegalArgumentException](PointGrid(Seq.empty))
    assertThrows[IllegalArgumentException](PointGrid(Seq(Seq())))
    assertThrows[IllegalArgumentException](PointGrid(Seq(Seq(), Seq())))
  }

  test("Not 2D grid") {
    assertThrows[IllegalArgumentException](PointGrid(Seq(Seq(1, 2), Seq(3))))
    assertThrows[IllegalArgumentException](PointGrid(Seq(Seq(1, 2), Seq(3, 4), Seq(5, 6, 7))))
  }

  test("pointAt") {
    val grid = PointGrid(List(
      List('A', 'B'),
      List('C', 'D'),
      List('E', 'F')))

    assert(grid.pointAt(0, 0) === 'A')
    assert(grid.pointAt(1, 0) === 'B')
    assert(grid.pointAt(0, 1) === 'C')
    assert(grid.pointAt(1, 1) === 'D')
    assert(grid.pointAt(0, 2) === 'E')
    assert(grid.pointAt(1, 2) === 'F')
  }

  test("pointAt - Out of bounds") {
    val grid = PointGrid(List(
      List('A','B'),
      List('C','D'),
      List('E','F')))

    assertThrows[RuntimeException](grid.pointAt(2,2))
    assertThrows[RuntimeException](grid.pointAt(3,0))
    assertThrows[RuntimeException](grid.pointAt(-1,0))
  }

  test("height") {
    val grid = PointGrid(List(
      List('A','B'),
      List('C','D'),
      List('E','F')))

    assert(grid.height === 3)
  }

  test("width") {
    val grid = PointGrid(List(
      List('A', 'B'),
      List('C', 'D'),
      List('E', 'F')))

    assert(grid.width === 2)
  }

  test("lineAt") {
    val grid = PointGrid(List(
      List('A', 'B'),
      List('C', 'D'),
      List('E', 'F')))

    assert(grid.lineAt(0) === List('A','B'))
    assert(grid.lineAt(1) === List('C','D'))
    assert(grid.lineAt(2) === List('E','F'))
  }

  test("lineAt - Out of bounds") {
    val grid = PointGrid(List(
      List('A', 'B'),
      List('C', 'D'),
      List('E', 'F')))

    assertThrows[IllegalArgumentException](grid.lineAt(-1))
    assertThrows[IllegalArgumentException](grid.lineAt(3))
  }

  test("map") {
    val grid = PointGrid(List(
      List('A', 'B'),
      List('C', 'D'),
      List('E', 'F')))

    def mapper(c: Char): Int = c.toInt - 64
    val gridExpected = PointGrid(List(List(1,2), List(3,4), List(5,6)))

    assert(grid.map(mapper) === gridExpected)
  }

}
