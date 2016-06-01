package konstructs.api

import java.util
import java.util.UUID

import org.scalatest.{ Matchers, WordSpec }

class KonstructingSpec extends WordSpec with Matchers {

  val One = new BlockTypeId("test", "1")
  val Two = new BlockTypeId("test", "2")
  val Three = new BlockTypeId("test", "3")

  val ClassOne = new BlockClassId("test", "One")
  val ClassTwo = new BlockClassId("test", "Two")

  val factory = new BlockFactory {

    override def getBlockType(typeId: BlockTypeId): BlockType = typeId match {
      case One =>
        new BlockType(Array(0,0,0,0,0,0), BlockShape.BLOCK, false, false, BlockState.SOLID, BlockType.NO_CLASSES)
      case Two =>
        new BlockType(Array(0,0,0,0,0,0), BlockShape.BLOCK, false, false, BlockState.SOLID, Array(ClassOne))
      case Three =>
        new BlockType(Array(0,0,0,0,0,0), BlockShape.BLOCK, false, false, BlockState.SOLID, Array(ClassOne, ClassTwo))
    }

    override def getBlockTypes: util.Map[BlockTypeId, BlockType] = ???

    override def getBlockTypeId(w: Int): BlockTypeId = ???

    override def createBlock(uuid: UUID, w: Int): Block = ???

    override def createBlock(w: Int): Block = ???

    override def getWMapping: util.Map[Integer, BlockTypeId] = ???

    override def getW(block: Block): Int = ???

    override def getW(stack: Stack): Int = ???

    override def getW(typeId: BlockTypeId): Int = ???

  }
  def s(t: BlockTypeId) = Stack.createFromBlock(new Block(null, t))
  def b(t: BlockTypeId) = new Block(null, t)
  def st(t: BlockTypeId) = new StackTemplate(new BlockOrClassId(t), 1)
  def st(t: BlockClassId) = new StackTemplate(new BlockOrClassId(t), 1)

  "A Inventory" should {
    "produce a 1x1 pattern for a 1x1 inventory" in {
      val view = new InventoryView(0,0,1,1)
      val i = new Inventory(Array(s(One)))
      i.getPattern(view) shouldEqual new Pattern(Array(s(One)), 1, 1)
    }
    "produce a 1x1 pattern for a 2x2 inventory if only one stack" in {
      val view = new InventoryView(0,0,2,2)
      val i = new Inventory(Array(s(One), null, null, null))
      i.getPattern(view) shouldEqual new Pattern(Array(s(One)), 1, 1)
    }
    "produce a 1x1 pattern for a 2x2 inventory if only one stack on another position" in {
      val view = new InventoryView(0,0,2,2)
      val i = new Inventory(Array(null, null, s(One), null))
      i.getPattern(view) shouldEqual new Pattern(Array(s(One)), 1, 1)
    }
    "produce a 1x2 pattern for a 2x2 inventory" in {
      val view = new InventoryView(0,0,2,2)
      val i = new Inventory(Array(s(One), s(Two), null, null))
      i.getPattern(view) shouldEqual new Pattern(Array(s(One), s(Two)), 1, 2)
    }
    "produce a 1x2 pattern for a 2x2 inventory on second row" in {
      val view = new InventoryView(0,0,2,2)
      val i = new Inventory(Array( null, null, s(One), s(Two)))
      i.getPattern(view) shouldEqual new Pattern(Array(s(One), s(Two)), 1, 2)
    }
    "produce a 2x1 pattern for a 2x2 inventory" in {
      val view = new InventoryView(0,0,2,2)
      val i = new Inventory(Array(s(One), null, s(Two), null))
      i.getPattern(view) shouldEqual new Pattern(Array(s(One), s(Two)), 2, 1)
    }
    "produce a 2x1 pattern for a 2x2 inventory on second column" in {
      val view = new InventoryView(0,0,2,2)
      val i = new Inventory(Array( null, s(One), null, s(Two)))
      i.getPattern(view) shouldEqual new Pattern(Array(s(One), s(Two)), 2, 1)
    }
    "produce no pattern if empty" in {
      val view = new InventoryView(0,0,1,1)
      val i = new Inventory(Array[Stack]())
      i.getPattern(view) shouldEqual null
    }
    "remove only the blocks of a 2x1 pattern for a 2x2 inventory" in {
      val i = new Inventory(Array(new Stack(Array(b(One), b(One))), null, s(Two), null))
      i.remove(new PatternTemplate(Array(st(One), st(Two)), 2, 1), factory) shouldEqual
        new Inventory(Array(s(One), null, null, null))
    }
    "remove all blocks of a 2x1 pattern for a 2x2 inventory" in {
      val i = new Inventory(Array(s(One), null, s(Two), null))
      i.remove(new PatternTemplate(Array(st(One), st(Two)), 2, 1), factory) shouldEqual
        new Inventory(Array(null, null, null, null))
    }
    "fail to remove a pattern from a too small inventory" in {
      val i = new Inventory(Array(s(One)))
      i.remove(new PatternTemplate(Array(st(One), st(Two)), 2, 1), factory) shouldEqual
        null
    }
    "fail to remove a pattern from an inventory that doesn't match" in {
      val i = new Inventory(Array(s(One), s(Three)))
      i.remove(new PatternTemplate(Array(st(One), st(Two)), 2, 1), factory) shouldEqual
        null
    }
    "fail to remove a pattern from an inventory that has extra stacks" in {
      val i = new Inventory(Array(s(One), s(Three)))
      i.remove(new PatternTemplate(Array(st(One)), 1, 1), factory) shouldEqual
        null
    }
    "fail to remove a pattern from an inventory with to few elements in stack" in {
      val i = new Inventory(Array(s(One)))
      i.remove(new PatternTemplate(Array(new StackTemplate(new BlockOrClassId(One), 2)), 1, 1), factory) shouldEqual
        null
    }
    "remove blocks pattern wise by a 2x1 pattern for a 2x2 inventory" in {
      val i = new Inventory(Array(new Stack(Array(b(One), b(One))), null, s(One), null))
      i.remove(new PatternTemplate(Array(st(One), st(One)), 2, 1), factory) shouldEqual
        new Inventory(Array(s(One), null, null, null))
    }
    "remove only the blocks of a 2x1 pattern for a 2x2 inventory based on class" in {
      val i = new Inventory(Array(new Stack(Array(b(One), b(One))), null, s(Two), null))
      i.remove(new PatternTemplate(Array(st(One), st(ClassOne)), 2, 1), factory) shouldEqual
        new Inventory(Array(s(One), null, null, null))
    }
    "remove all blocks of a 2x1 pattern for a 2x2 inventory based on several classes" in {
      val i = new Inventory(Array(s(Two), null, s(Three), null))
      i.remove(new PatternTemplate(Array(st(ClassOne), st(ClassOne)), 2, 1), factory) shouldEqual
        new Inventory(Array(null, null, null, null))
    }
  }
  "A Pattern" should {
    "contain a 1x1 pattern in a 1x1 pattern" in {
      new Pattern(Array(s(One)), 1, 1).contains(new PatternTemplate(Array(st(One)), 1, 1), factory) shouldEqual true
    }
    "contain a 1x1 pattern in a 1x1 pattern with more blocks" in {
      new Pattern(Array(new Stack(Array(b(One), b(One), b(One)))), 1, 1).contains(new PatternTemplate(Array(st(One)), 1, 1), factory) shouldEqual true
    }
    "not contain a 1x1 pattern in an empty" in {
      new Pattern(Array(null), 1, 1).contains(new PatternTemplate(Array(st(One)), 1, 1), factory) shouldEqual false
    }
    "contain a 1x2 pattern in a 1x2 pattern" in {
      new Pattern(Array(s(One), s(Two)), 1, 2).contains(new PatternTemplate(Array(st(One), st(Two)), 1, 2), factory) shouldEqual true
    }
    "contain a 1x2 pattern in a 1x2 pattern with more blocks" in {
      new Pattern(Array(new Stack(Array(b(One), b(One), b(One))), s(Two)), 1, 2).contains(new PatternTemplate(Array(st(One), st(Two)), 1, 2), factory) shouldEqual true
    }
    "not contain a 1x2 pattern in an invalid 1x2 pattern" in {
      new Pattern(Array(s(Three), s(Two)), 1, 2).contains(new PatternTemplate(Array(st(One), st(Two)), 1, 2), factory) shouldEqual false
    }
    "contain a 1x1 pattern in a 1x1 pattern based on class" in {
      new Pattern(Array(s(Two)), 1, 1).contains(new PatternTemplate(Array(st(ClassOne)), 1, 1), factory) shouldEqual true
    }
    "contain a 1x1 pattern in a 1x1 pattern based on class (mutliple classes)" in {
      new Pattern(Array(s(Three)), 1, 1).contains(new PatternTemplate(Array(st(ClassOne)), 1, 1), factory) shouldEqual true
    }
    "not contain a 1x1 pattern in a 1x1 pattern based on mismatching class" in {
      new Pattern(Array(s(One)), 1, 1).contains(new PatternTemplate(Array(st(ClassOne)), 1, 1), factory) shouldEqual false
    }
    "contain a 1x2 pattern in a 1x2 pattern with more blocks based on classes" in {
      new Pattern(Array(new Stack(Array(b(Two), b(Two), b(Two))), s(Three)), 1, 2).contains(new PatternTemplate(Array(st(ClassOne), st(ClassTwo)), 1, 2), factory) shouldEqual true
    }
  }

}
