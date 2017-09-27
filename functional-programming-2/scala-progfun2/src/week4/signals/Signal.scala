package week4.signals

class Signal[T](expr: => T) {
  def apply(): T = ???
}

object NoSignal extends Signal[Nothing](???){???}

object Signal {
  private val caller = new StackableVariable[Signal[_]](NoSignal)  
  def apply[T](expr: => T) = new Signal(expr)
}