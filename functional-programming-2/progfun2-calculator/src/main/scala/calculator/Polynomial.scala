package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal(computeDelta(a(), b(), c()))
  }
  
  private def computeDelta(a: Double, b: Double, c:Double): Double = 
    (b * b) - (4 * a * c)

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Signal(computeSolutions(computeDelta(a, b, c)(), a(), b()))
  }
  
  private def computeSolutions(delta: Double, a: Double, b: Double ): Set[Double] = 
    if(delta < 0) Set() 
    else if (delta == 0) Set(-b/(2*a)) 
    else Set((-b+math.sqrt(delta))/(2*a),(-b-math.sqrt(delta))/(2*a)) 
}
