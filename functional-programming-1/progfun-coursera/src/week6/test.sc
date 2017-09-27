package week6

object test {
  val list = List(('a',1),('b',1),('c',2))        //> list  : List[(Char, Int)] = List((a,1), (b,1), (c,2))
  list.toMap                                      //> res0: scala.collection.immutable.Map[Char,Int] = Map(a -> 1, b -> 1, c -> 2)
	
	(for {
			(char, freq) <- list
			times <- 1 to freq
		} yield char).mkString            //> res1: String = abcc
}