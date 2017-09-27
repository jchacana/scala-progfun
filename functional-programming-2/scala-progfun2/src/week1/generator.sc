package week1

object generator {

	println("Welcome to Scala")               //> Welcome to Scala

	trait Generator[+T] {
		self =>
		
		def generate: T
		
		def map[S](f: T => S): Generator[S] = new Generator[S]{
			def generate = f(self.generate)
		}
		
		def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S]{
			def generate = f(self.generate).generate
		}
	}

	trait Tree
	case class Inner(left: Tree, right: Tree) extends Tree
	case class Leaf(x: Int) extends Tree
  
  val integers = new Generator[Int] {
  	def generate = scala.util.Random.nextInt()
  }                                               //> integers  : week1.generator.Generator[Int] = week1.generator$$anonfun$main$1
                                                  //| $$anon$3@3b6eb2ec
  
  val booleans = integers.map(_ >= 0)             //> booleans  : week1.generator.Generator[Boolean] = week1.generator$$anonfun$ma
                                                  //| in$1$Generator$1$$anon$1@3f3afe78
  
  def leafs: Generator[Leaf] = for{
  	x <- integers
  } yield Leaf(x)                                 //> leafs: => week1.generator.Generator[week1.generator.Leaf]
  
  def inners: Generator[Inner] = for {
  	l <- trees
  	r <- trees
  } yield Inner(l, r)                             //> inners: => week1.generator.Generator[week1.generator.Inner]
  
  def trees: Generator[Tree] = for {
  	isLeaf <- booleans
  	tree <- if(isLeaf) leafs else inners
  } yield tree                                    //> trees: => week1.generator.Generator[week1.generator.Tree]
  
  trees.generate                                  //> res0: week1.generator.Tree = Inner(Leaf(-1777590683),Inner(Leaf(252379404),L
                                                  //| eaf(-1788717137)))
  
}