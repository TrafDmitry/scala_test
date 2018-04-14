import Tree._

object Launcher extends App {

  val leaf1 = Leaf(1)
  val leaf2 = Leaf(2)
  val leaf3 = Leaf(3)
  val leaf4 = Leaf(4)
  val leaf5 = Leaf(5)

  val listLeaves = List(leaf2, leaf1, leaf1, leaf1, leaf1, leaf3)
  val listLeaves1 = List(leaf2, leaf3, leaf1, leaf4)
  val listLeaves2 = List(leaf1)
  val listLeaves3 = List(leaf2, leaf3, leaf1, leaf5)

  val node2 = Node(Array(), listLeaves2)
  val node3 = Node(Array(node2), listLeaves2)
  val node1 = Node(Array(node2, node3), listLeaves)

  val tree = node1.sort(node1, 3)
  tree.print(0)

}



