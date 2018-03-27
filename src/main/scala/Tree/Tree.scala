package Tree

case class Tree(root: Node, W: Int) {

  require(W >= 0, "Error, only unsigned or null value")

  private def isRedundantTree = W > 0

  private def rebuildRoot(node: Array[Node], newLeaves: List[Leaf], redundantLeaves: List[Leaf]): Node = {
    if (redundantLeaves.isEmpty) Node(node, newLeaves)
    else {
       if (node.isEmpty){
         Node(Array(Node(Array(), redundantLeaves)), newLeaves)
       } else {

         val firstElem = Array(Node(node.head.node, node.head.setLeaves(redundantLeaves)))
         val anotherElems = node.drop(0)
         Node(firstElem ++ anotherElems , newLeaves)
       }
    }
  }

  private def mySort(list: List[Leaf]): List[Leaf] = {

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
    sort(list)((l1: Leaf, l2: Leaf) => l1.weight < l2.weight)
  }

  def sort(): Tree = {
    val sortTree = Tree(Node(root.node, mySort(root.leaves)), W)

    if (isRedundantTree) {
      Tree(rebuildRoot(sortTree.root.node, getNewLeaves(sortTree.root.leaves), getRedundantLeaves(sortTree.root.leaves)), W)
    } else {
      sortTree
    }
  }

  private def getNewLeaves(list: List[Leaf]): List[Leaf] = {
    var acc = 0
    list takeWhile  (l => if(acc >= W) false else {acc += l.weight; true })
  }

  private def getRedundantLeaves(list: List[Leaf]): List[Leaf] = {
    var acc = 0
    list dropWhile (l => if(acc >= W) false else { acc += l.weight; true })
  }
}