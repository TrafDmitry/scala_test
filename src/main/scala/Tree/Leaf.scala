package Tree

case class Leaf (weight: Int ){
  require(weight > 0, "Error, only unsigned not null value")
}
