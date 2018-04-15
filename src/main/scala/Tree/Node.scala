package Tree

case class Node(node: Array[Node], leaves: List[Leaf]) {

  /**
    * @param offset
    */
  def print(offset: Int):Unit = {

    val padding = List.fill(offset)(' ').mkString

    println(s"$padding - Node")

    println(s"$padding     leaves: "+leaves.map(_.weight).mkString(","))
    println(s"$padding     nodes:")

    node.foreach {subNode=>
      subNode.print(offset+2)
    }
  }

  /**
    * @param newList
    * @return List
    */
  private def appendLeaves(newList: List[Leaf]): List[Leaf] = {
    def concat[T](list1: List[T], list2: List[T]): List[T] = list1 match {
      case List() => list2
      case head :: tail => head :: concat(tail, list2)
    }
    concat(leaves, newList)
  }

  /**
    * @param list
    * @param W
    * @return List
    */
  private def getNewLeaves(list: List[Leaf], W: Int): List[Leaf] = {
    var acc = 0
    list takeWhile  (l => if(acc >= W) false else {acc += l.weight; true })
  }

  /**
    *
    * @param list
    * @param W
    * @return List
    */
  private def getRedundantLeaves(list: List[Leaf], W: Int): List[Leaf] = {
    var acc = 0
    list dropWhile (l => if(acc >= W) false else { acc += l.weight; true })
  }

  /**
    * @param node
    * @param W
    * @param moreLeaves
    * @return Node
    */
  def sort(node: Node, W: Int, moreLeaves: List[Leaf] = List()): Node = {
    def loop(node: Node, W: Int, index: Int, moreLeaves: List[Leaf] = List()): Node = node match {
      case Node(Array(), List()) =>
        node
      case Node(Array(), _) =>
        if (index == 0) {
          Node(node.node, getNewLeaves(sortLeaves(node.appendLeaves(moreLeaves)), W))
        } else {
          Node(node.node, getNewLeaves(sortLeaves(node.leaves), W))
        }
      case Node(_, _) =>
        val sortedLeaves = sortLeaves(node.appendLeaves(moreLeaves))
        val newLeaves = getNewLeaves(sortedLeaves, W)
        val redundantLeaves = getRedundantLeaves(sortedLeaves, W)
        Node(node.node.map(subNode => loop(subNode, W, node.node.indexOf(subNode), redundantLeaves)), newLeaves)

    }
    loop(node, W, 0, moreLeaves)
  }

  /**
    * @param leaves
    * @return List
    */
  private def sortLeaves(leaves: List[Leaf]): List[Leaf] = {
    def sort(list: List[Leaf])(compare: (Leaf, Leaf) => Boolean): List[Leaf] = {
      val n = list.length / 2
      if (n == 0) list
      else {
        def merge(list1: List[Leaf], list2: List[Leaf]): List[Leaf] = (list1, list2) match {
          case (Nil, list2) => list2
          case (list1, Nil) => list1
          case (head1 :: tail1, head2 :: tail2) =>
            if (compare(head1, head2)) head1 :: merge(tail1, list2)
            else head2 :: merge(list1, tail2)
        }

        val (fst, snd) = list splitAt n
        merge(sort(fst)(compare), sort(snd)(compare))
      }
    }
    sort(leaves)((l1: Leaf, l2: Leaf) => l1.weight < l2.weight)
  }
}
