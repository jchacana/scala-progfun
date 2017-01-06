package week2

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val problem = new Pouring(Vector(4, 7))         //> problem  : week2.Pouring = week2.Pouring@47f6473
  
  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with w
                                                  //| eek2.test.problem.Move] = Vector(Empty(0), Empty(1), Fill(0), Fill(1), Pour(
                                                  //| 0,1), Pour(1,0))
  problem.solutions(6)                            //> res1: Stream[week2.test.problem.Path] = Stream(Fill(1) Pour(1,0) Empty(0) Po
                                                  //| ur(1,0) Fill(1) Pour(1,0)--> Vector(4, 6), ?)
                        
  
  
}