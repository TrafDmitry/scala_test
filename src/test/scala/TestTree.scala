import org.scalatest._
import Tree._

class TestTree extends FlatSpec {

  val b1 = Leaf(1)
  val b2 = Leaf(2)
  val b3 = Leaf(3)
  val b4 = Leaf(4)

  val a1: Node = Node(Array(), List())

//  private def initialized(W: Int): Tree = {
//    val treeLeaves: List[Leaf] = List(b2, b4, b3, b1)
//    val node: Node = Node(Array(a1), treeLeaves)
//    val tree: Tree = Tree(node, W)
//    tree
//  }
//
//  val tree: Tree = this.initialized(0)
//  "Tree.A tree leaves" should "be sorted" in {
//    val sortedLeaves: List[Leaf] = List(b1, b2, b3, b4)
//    val sortTree = tree.sort()
//    assert(sortedLeaves === sortTree.root.leaves)
//  }
//
//  val redundantTree: Tree = this.initialized(3)
//  "Redundant tree.A tree leaves" should "be sorted" in {
//    val sortedRedundantTreeLeaves: List[Leaf] = List(b1, b2)
//    val childrenNodeLeaves: List[Leaf] = List(b3, b4)
//    val redundantSortTree = redundantTree.sort()
//    assert(sortedRedundantTreeLeaves === redundantSortTree.root.leaves)
//    assert(childrenNodeLeaves === redundantSortTree.root.node(0).leaves)
//  }

}
