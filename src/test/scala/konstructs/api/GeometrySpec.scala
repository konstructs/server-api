package konstructs.api

import org.scalatest.{ Matchers, WordSpec }

import konstructs.api._

class GeometrySpec extends WordSpec with Matchers {


  "A Box" should {

    "return box Position(0,0,0) until Position(2,2,2) when create(Position(1,1,1), Position(0,0,0))" in {
      Box.create(new Position(1,1,1), new Position(0,0,0)) shouldEqual new Box(new Position(0,0,0), new Position(2,2,2))
    }

    "return box Position(0,0,0) until Position(1,1,2) when create(Position(0,0,1), Position(0,0,0))" in {
      Box.create(new Position(0,0,1), new Position(0,0,0)) shouldEqual new Box(new Position(0,0,0), new Position(1,1,2))
    }

    "have 2 blocks when create(Position(0,0,1), Position(0,0,0))" in {
      Box.create(new Position(0,0,1), new Position(0,0,0)).getNumberOfBlocks shouldEqual 2
    }

    "have 27 blocks when create(Position(-1,1,-1), Position(1,-1,1))" in {
      Box.create(new Position(-1,1,-1), new Position(1,-1,1)).getNumberOfBlocks shouldEqual 27
    }

    "return box Position(-2,-20,-2) until Position(-1,1,-1) when createInDirection(Position(-2,0,-2), Direction.DOWN, 20)" in {
      Box.createInDirection(new Position(-2, 0, -2), Direction.DOWN, 20) shouldEqual new Box(new Position(-2,-20,-2), new Position(-1,1,-1))
    }

    "contain 0, 0, 0 in (-32, 0, -32) (32, 0, 32)" in {
      new Box(new Position(-32, 0, -32), new Position(32, 1, 32)).contains(new Position(0, 0, 0)) shouldEqual true
    }

    "throw exception if end is bigger than start" in {
      intercept[IllegalArgumentException] {
        new Box(new Position(0, 0, -67), new Position(0, 0, -69))
      }
    }

    "return a correct box size" in {
      new Box(new Position(0, 0, 0), new Position(1, 1, 1)).getNumberOfBlocks shouldEqual 1
      new Box(new Position(0, 0, 0), new Position(2, 2, 2)).getNumberOfBlocks shouldEqual 8
      new Box(new Position(-1, -1, -1), new Position(1, 1, 1)).getNumberOfBlocks shouldEqual 8
    }

    "return a correct internal indexes (1 block box)" in {
      val box = new Box(new Position(0, 0, 0), new Position(1, 1, 1))
      box.arrayIndex(new Position(0, 0, 0)) shouldEqual 0
    }

    "return a correct internal indexes (4 block box, no z)" in {
      val box = new Box(new Position(0, 0, 0), new Position(2, 2, 1))
      box.arrayIndex(new Position(0, 0, 0)) shouldEqual 0
      box.arrayIndex(new Position(1, 1, 0)) shouldEqual 3
    }

    "return a correct internal indexes (8 block box)" in {
      val box = new Box(new Position(0, 0, 0), new Position(2, 2, 2))
      box.arrayIndex(new Position(0, 0, 0)) shouldEqual 0
      box.arrayIndex(new Position(1, 1, 0)) shouldEqual 6
      box.arrayIndex(new Position(1, 1, 1)) shouldEqual 7
    }

    "return a correct internal indexes (45 block box)" in {
      val box = new Box(new Position(0, 0, 0), new Position(3, 3, 5))
      box.arrayIndex(new Position(0, 0, 0)) shouldEqual 0
      box.arrayIndex(new Position(1, 1, 0)) shouldEqual 20
      box.arrayIndex(new Position(2, 2, 4)) shouldEqual 44
    }

    "return a correct internal indexes (8 block box away from center)" in {
      val box = new Box(new Position(1, 1, 1), new Position(3, 3, 3))
      box.arrayIndex(new Position(1, 1, 1)) shouldEqual 0
      box.arrayIndex(new Position(2, 2, 1)) shouldEqual 6
      box.arrayIndex(new Position(2, 2, 2)) shouldEqual 7
    }

  }

}
