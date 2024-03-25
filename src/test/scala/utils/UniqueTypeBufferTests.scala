package utils

import org.scalatest.FunSuite

class UniqueTypeBufferTests extends FunSuite {

  class Animal
  class Dog extends Animal
  class Cat extends Animal
  class FluffyCat extends Cat

  test("Unique type buffer") {
    val buffer = new UniqueTypeBuffer[Animal]

    val animal1 = new Animal
    val dog1 = new Dog
    val dog2 = new Dog
    val cat1 = new Cat
    val fluffyCat1 = new FluffyCat
    val fluffyCat2 = new FluffyCat

    buffer.add(animal1).add(dog1).add(dog2).add(cat1).add(fluffyCat1).add(fluffyCat2)

    val expectedSeq = Seq(animal1, dog2, cat1, fluffyCat2)

    assert(buffer.toSeq === expectedSeq)
  }

}
