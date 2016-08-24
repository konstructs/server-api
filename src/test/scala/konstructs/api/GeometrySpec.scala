package konstructs.api

import org.scalatest.{ Matchers, WordSpec }

class GeometrySpec extends WordSpec with Matchers {


  "A Box" should {

    "return box Position(0,0,0) until Position(2,2,2) when create(Position(1,1,1), Position(0,0,0))" in {
      new InclusiveBox(new Position(1,1,1), new Position(0,0,0)).getBox shouldEqual new Box(new Position(0,0,0), new Position(2,2,2))
    }

    "return box Position(0,0,0) until Position(1,1,2) when create(Position(0,0,1), Position(0,0,0))" in {
      new InclusiveBox(new Position(0,0,1), new Position(0,0,0)).getBox shouldEqual new Box(new Position(0,0,0), new Position(1,1,2))
    }

    "have 2 blocks when create(Position(0,0,1), Position(0,0,0))" in {
      new InclusiveBox(new Position(0,0,1), new Position(0,0,0)).getBox.getNumberOfBlocks shouldEqual 2
    }

    "have 27 blocks when create(Position(-1,1,-1), Position(1,-1,1))" in {
      new InclusiveBox(new Position(-1,1,-1), new Position(1,-1,1)).getBox.getNumberOfBlocks shouldEqual 27
    }

    "return box Position(-2,-20,-2) until Position(-1,1,-1) when DirectionalLine(Position(-2,0,-2), Direction.DOWN, 20)" in {
      new DirectionalLine(new Position(-2, 0, -2), Direction.DOWN, 20).getBox shouldEqual new Box(new Position(-2,-20,-2), new Position(-1,1,-1))
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
  "An Orientation face pointed at" should {
    "leave a Direction unchanged" in {
      Orientation.get(Direction.UP, Rotation.IDENTITY).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.RIGHT
    }

    "opposite direction is always pointing down" in {
      Orientation.get(Direction.UP, Rotation.IDENTITY).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.DOWN
      Orientation.get(Direction.LEFT, Rotation.IDENTITY).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.DOWN
      Orientation.get(Direction.RIGHT, Rotation.IDENTITY).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.DOWN
      Orientation.get(Direction.DOWN, Rotation.IDENTITY).translateFacePointedAt(Direction.UP) shouldEqual Direction.DOWN
      Orientation.get(Direction.FORWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.DOWN
      Orientation.get(Direction.BACKWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.DOWN
    }

    "same direction is always pointing up" in {
      Orientation.get(Direction.UP, Rotation.IDENTITY).translateFacePointedAt(Direction.UP) shouldEqual Direction.UP
      Orientation.get(Direction.UP, Rotation.LEFT).translateFacePointedAt(Direction.UP) shouldEqual Direction.UP
      Orientation.get(Direction.UP, Rotation.RIGHT).translateFacePointedAt(Direction.UP) shouldEqual Direction.UP
      Orientation.get(Direction.UP, Rotation.HALF).translateFacePointedAt(Direction.UP) shouldEqual Direction.UP
      Orientation.get(Direction.LEFT, Rotation.IDENTITY).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.UP
      Orientation.get(Direction.LEFT, Rotation.LEFT).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.UP
      Orientation.get(Direction.LEFT, Rotation.RIGHT).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.UP
      Orientation.get(Direction.LEFT, Rotation.HALF).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.UP
      Orientation.get(Direction.RIGHT, Rotation.IDENTITY).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.UP
      Orientation.get(Direction.RIGHT, Rotation.LEFT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.UP
      Orientation.get(Direction.RIGHT, Rotation.RIGHT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.UP
      Orientation.get(Direction.RIGHT, Rotation.HALF).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.UP
      Orientation.get(Direction.DOWN, Rotation.IDENTITY).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.UP
      Orientation.get(Direction.DOWN, Rotation.LEFT).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.UP
      Orientation.get(Direction.DOWN, Rotation.RIGHT).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.UP
      Orientation.get(Direction.DOWN, Rotation.HALF).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.UP
      Orientation.get(Direction.FORWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.UP
      Orientation.get(Direction.FORWARD, Rotation.LEFT).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.UP
      Orientation.get(Direction.FORWARD, Rotation.RIGHT).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.UP
      Orientation.get(Direction.FORWARD, Rotation.HALF).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.UP
      Orientation.get(Direction.BACKWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.UP
      Orientation.get(Direction.BACKWARD, Rotation.LEFT).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.UP
      Orientation.get(Direction.BACKWARD, Rotation.RIGHT).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.UP
      Orientation.get(Direction.BACKWARD, Rotation.HALF).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.UP
    }

    "when direction is UP rotation direction points forward" in {
      Orientation.get(Direction.UP, Rotation.IDENTITY).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.FORWARD
      Orientation.get(Direction.UP, Rotation.LEFT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.FORWARD
      Orientation.get(Direction.UP, Rotation.RIGHT).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.FORWARD
      Orientation.get(Direction.UP, Rotation.HALF).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.FORWARD
    }

    "when direction is LEFT rotation direction points FORWARD" in {
      Orientation.get(Direction.LEFT, Rotation.IDENTITY).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.FORWARD
      Orientation.get(Direction.LEFT, Rotation.LEFT).translateFacePointedAt(Direction.UP) shouldEqual Direction.FORWARD
      Orientation.get(Direction.LEFT, Rotation.RIGHT).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.FORWARD
      Orientation.get(Direction.LEFT, Rotation.HALF).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.FORWARD
    }

    "when direction is FORWARD rotation direction points LEFT" in {
      Orientation.get(Direction.FORWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.LEFT
      Orientation.get(Direction.FORWARD, Rotation.LEFT).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.LEFT
      Orientation.get(Direction.FORWARD, Rotation.RIGHT).translateFacePointedAt(Direction.UP) shouldEqual Direction.LEFT
      Orientation.get(Direction.FORWARD, Rotation.HALF).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.LEFT
    }

    "when direction is BACKWARD rotation direction points RIGHT" in {
      Orientation.get(Direction.BACKWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.LEFT
      Orientation.get(Direction.BACKWARD, Rotation.LEFT).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.RIGHT
      Orientation.get(Direction.BACKWARD, Rotation.RIGHT).translateFacePointedAt(Direction.UP) shouldEqual Direction.RIGHT
      Orientation.get(Direction.BACKWARD, Rotation.HALF).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.LEFT
    }

    "Swap place on left and right when downwards" in {
      Orientation.get(Direction.DOWN, Rotation.IDENTITY).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.LEFT
      Orientation.get(Direction.DOWN, Rotation.IDENTITY).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.RIGHT
    }

    "Leave forward and backwards untouched when downwards" in {
      Orientation.get(Direction.DOWN, Rotation.IDENTITY).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.FORWARD
      Orientation.get(Direction.DOWN, Rotation.IDENTITY).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.BACKWARD
    }

    "leave a Direction unchanged (when upside down)" in {
      Orientation.get(Direction.DOWN, Rotation.IDENTITY).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.FORWARD
    }

    "rotate direction right to the left around the UP axis" in {
      Orientation.get(Direction.UP, Rotation.LEFT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.FORWARD
    }

    "rotate direction left to the left around the UP axis" in {
      Orientation.get(Direction.UP, Rotation.LEFT).translateFacePointedAt(Direction.LEFT) shouldEqual Direction.BACKWARD
    }

    "rotate direction forward to the left around the UP axis" in {
      Orientation.get(Direction.UP, Rotation.LEFT).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.LEFT
    }

    "rotate direction right to the right around the UP axis" in {
      Orientation.get(Direction.UP, Rotation.RIGHT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.BACKWARD
    }

    "rotate direction right to the half way around the UP axis" in {
      Orientation.get(Direction.UP, Rotation.HALF).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.LEFT
    }

    "rotate direction forward to the half way around the UP axis" in {
      Orientation.get(Direction.UP, Rotation.HALF).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.BACKWARD
    }

    "rotate direction up to right when on LEFT axis" in {
      Orientation.get(Direction.LEFT, Rotation.IDENTITY).translateFacePointedAt(Direction.UP) shouldEqual Direction.RIGHT
    }

    "rotate direction down to left when on LEFT axis" in {
      Orientation.get(Direction.LEFT, Rotation.IDENTITY).translateFacePointedAt(Direction.DOWN) shouldEqual Direction.LEFT
    }

    "rotate direction right to the left around the LEFT axis" in {
      Orientation.get(Direction.LEFT, Rotation.LEFT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.DOWN
    }

    "rotate direction backward to the left around the LEFT axis" in {
      Orientation.get(Direction.LEFT, Rotation.LEFT).translateFacePointedAt(Direction.BACKWARD) shouldEqual Direction.RIGHT
    }

    "rotate direction up to the left around the LEFT axis" in {
      Orientation.get(Direction.LEFT, Rotation.LEFT).translateFacePointedAt(Direction.UP) shouldEqual Direction.FORWARD
    }

    "rotate direction forward to the right around the LEFT axis" in {
      Orientation.get(Direction.LEFT, Rotation.RIGHT).translateFacePointedAt(Direction.FORWARD) shouldEqual Direction.RIGHT
    }

    "rotate direction right to the half way around the LEFT axis" in {
      Orientation.get(Direction.LEFT, Rotation.HALF).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.DOWN
    }

    "rotate direction up to forward when on FORWARD axis" in {
      Orientation.get(Direction.FORWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.UP) shouldEqual Direction.BACKWARD
    }

    "leave direction right unchanged when on FORWARD axis" in {
      Orientation.get(Direction.FORWARD, Rotation.IDENTITY).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.RIGHT
    }

    "rotate direction right to the left around the FORWARD axis" in {
      Orientation.get(Direction.FORWARD, Rotation.LEFT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.FORWARD
    }

    "rotate direction right to the right around the FORWARD axis" in {
      Orientation.get(Direction.FORWARD, Rotation.RIGHT).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.BACKWARD
    }

    "rotate direction right to the half way around the FORWARD axis" in {
      Orientation.get(Direction.FORWARD, Rotation.HALF).translateFacePointedAt(Direction.RIGHT) shouldEqual Direction.LEFT
    }

  }

  "An Orientation face pointing in" should {

    "rotate forward around UP axis" in {
      Orientation.get(Direction.UP, Rotation.IDENTITY).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.FORWARD
      Orientation.get(Direction.UP, Rotation.RIGHT).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.LEFT
      Orientation.get(Direction.UP, Rotation.LEFT).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.RIGHT
      Orientation.get(Direction.UP, Rotation.HALF).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.BACKWARD
    }

    "rotate UP around UP axis" in {
      Orientation.get(Direction.UP, Rotation.IDENTITY).translateFacePointingIn(Direction.UP) shouldEqual Direction.UP
      Orientation.get(Direction.UP, Rotation.RIGHT).translateFacePointingIn(Direction.UP) shouldEqual Direction.UP
      Orientation.get(Direction.UP, Rotation.LEFT).translateFacePointingIn(Direction.UP) shouldEqual Direction.UP
      Orientation.get(Direction.UP, Rotation.HALF).translateFacePointingIn(Direction.UP) shouldEqual Direction.UP
    }

    "rotate forward around LEFT axis" in {
       Orientation.get(Direction.LEFT, Rotation.IDENTITY).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.FORWARD
      Orientation.get(Direction.LEFT, Rotation.RIGHT).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.DOWN
      Orientation.get(Direction.LEFT, Rotation.LEFT).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.UP
      Orientation.get(Direction.LEFT, Rotation.HALF).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.BACKWARD
     }

    "rotate forward around FORWARD axis" in {
      Orientation.get(Direction.FORWARD, Rotation.IDENTITY).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.DOWN
      Orientation.get(Direction.FORWARD, Rotation.RIGHT).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.LEFT
      Orientation.get(Direction.FORWARD, Rotation.LEFT).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.RIGHT
      Orientation.get(Direction.FORWARD, Rotation.HALF).translateFacePointingIn(Direction.FORWARD) shouldEqual Direction.UP
    }

    "rotate right around FORWARD axis" in {
      Orientation.get(Direction.FORWARD, Rotation.IDENTITY).translateFacePointingIn(Direction.RIGHT) shouldEqual Direction.RIGHT
      Orientation.get(Direction.FORWARD, Rotation.RIGHT).translateFacePointingIn(Direction.RIGHT) shouldEqual Direction.DOWN
      Orientation.get(Direction.FORWARD, Rotation.LEFT).translateFacePointingIn(Direction.RIGHT) shouldEqual Direction.UP
      Orientation.get(Direction.FORWARD, Rotation.HALF).translateFacePointingIn(Direction.RIGHT) shouldEqual Direction.LEFT
    }

  }
}
