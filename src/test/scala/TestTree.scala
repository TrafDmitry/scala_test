import org.scalatest._
import Tree._

class TestTree extends FlatSpec {

  val b1 = Leaf(1)
  val b2 = Leaf(2)
  val b3 = Leaf(3)
  val b4 = Leaf(4)

  val a1: Node = Node(Array(), List())


  val treeLeaves: List[Leaf] = List(b2, b4, b3, b1)
  val node: Node = Node(Array(a1), List())
  val tree = Node(Array(node), treeLeaves)

  "Redundant tree.A tree leaves" should "be sorted" in {
    val sortedRedundantTreeLeaves = List(b1, b2)
    val childrenNodeLeaves = List(b3)
    val sortTree = tree.sort(tree, 3)
    assert(sortedRedundantTreeLeaves === sortTree.leaves)
    assert(childrenNodeLeaves === sortTree.node(0).leaves)
  }

}
