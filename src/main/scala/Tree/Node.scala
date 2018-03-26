package Tree

case class Node(node: Array[Node], leaves: List[Leaf]) {

  def setLeaves(newList: List[Leaf]): List[Leaf] = {
    def concat[T](list1: List[T], list2: List[T]): List[T] = list1 match {
      case List() => list2
      case head :: tail => head :: concat(tail, list2)
    }
    concat(leaves, newList)
  }
}
