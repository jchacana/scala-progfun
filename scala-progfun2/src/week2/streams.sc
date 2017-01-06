package week2

object streams {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  (1 to 1000).toStream                            //> res0: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  
  def streamRange(lo: Int, hi: Int): Stream[Int] =
  	if(lo >= hi) Stream.empty
  	else Stream.cons(lo, streamRange(lo + 1, hi))
                                                  //> streamRange: (lo: Int, hi: Int)Stream[Int]
  def listRange(lo: Int, hi: Int): List[Int] =
  	if(lo >= hi) Nil
  	else lo :: listRange(lo + 1, hi)          //> listRange: (lo: Int, hi: Int)List[Int]
  
  
  listRange(1, 10)                                //> res1: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  streamRange(1, 10).tail.tail.tail               //> res2: scala.collection.immutable.Stream[Int] = Stream(4, ?)
  
}