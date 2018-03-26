package Tree

case class Tree(var tree: Node, W: Int) {

  require(W >= 0, "Error, only unsigned or null value")

  private def isRedundant = W > 0

  private def rebuildTree(node: Array[Node], thisLeaves: List[Leaf], redundantLeaves: List[Leaf]): Node = {
    if (redundantLeaves.isEmpty) Node(node, thisLeaves)
    else {
       if (tree.node.isEmpty){
         Node(Array(Node(Array(), redundantLeaves)), thisLeaves)
       } else {

         val firstElem = Array(Node(tree.node.head.node, tree.node.head.setLeaves(redundantLeaves)))
         val anotherElems = tree.node.drop(0)
         Node(firstElem ++ anotherElems , thisLeaves)
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

  def sort(): Unit = {
    tree = rebuildTree(tree.node, mySort(tree.leaves), List())
    if(isRedundant) {
      tree = rebuildTree(tree.node, getNewLeaves(tree.leaves), getRedundantLeaves(tree.leaves))
    }
  }

  private def getNewLeaves(list: List[Leaf]): List[Leaf] = {
    var acc = 0
    tree.leaves takeWhile  (l => if(acc >= W) false else {acc += l.weight; true })
  }

  private def getRedundantLeaves(list: List[Leaf]): List[Leaf] = {
    var acc = 0
    tree.leaves dropWhile (l => if(acc >= W) false else { acc += l.weight; true })
  }
}