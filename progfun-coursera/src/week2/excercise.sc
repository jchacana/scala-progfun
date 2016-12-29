package week2

object excercise {

  def fact(n: Int): Int = {
    def loop(acc: Int, n: Int): Int = {
      if (n == 0) acc
      else loop(acc * n, n - 1)
    }
    loop(1, n)
  }                                               //> fact: (n: Int)Int

  fact(5)                                         //> res0: Int = 120



  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  	if(a > b) zero
  	else combine(f(a), mapReduce(f, combine, zero)(a +1, b))
                                                  //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:
                                                  //|  Int)Int


  def sum(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0) (a, b)
                                                  //> sum: (f: Int => Int)(a: Int, b: Int)Int
  sum(x => x)(3, 5)                               //> res1: Int = 12
  


  def product(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
                                                  //> product: (f: Int => Int)(a: Int, b: Int)Int
  product(x => x * x)(3, 4)                       //> res2: Int = 144


  
  def factorial(n: Int): Int = product(x => x)(1, n)
                                                  //> factorial: (n: Int)Int
  factorial(5)                                    //> res3: Int = 120
  
  
	
}