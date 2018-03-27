import Tree._

object Launcher extends App {

  val leaf1 = Leaf(1)
  val leaf2 = Leaf(2)
  val leaf3 = Leaf(3)
  val leaf4 = Leaf(4)
  val leaf5 = Leaf(5)

  val listLeaves = List(leaf2, leaf3, leaf1, leaf5)

  val node2 = Node(Array(), List(leaf4))
  val node3 = Node(Array(), List(leaf4))
  val node1 = Node(Array(node2, node3), List(leaf4))

  val node = Node(Array(node1), listLeaves)
  val tree = Tree(node, 3)


}



