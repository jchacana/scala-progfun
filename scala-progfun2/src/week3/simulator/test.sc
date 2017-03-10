 package week3.simulator

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  object sim extends Circuits with Parameters
  import sim._
  
  val in1, in2, sum, carry = new Wire             //> in1  : week3.simulator.test.sim.Wire = week3.simulator.Gates$Wire@1e643faf
                                                  //| in2  : week3.simulator.test.sim.Wire = week3.simulator.Gates$Wire@6e8dacdf
                                                  //| sum  : week3.simulator.test.sim.Wire = week3.simulator.Gates$Wire@7a79be86
                                                  //| carry  : week3.simulator.test.sim.Wire = week3.simulator.Gates$Wire@34ce8af7
                                                  //| 
  halfAdder(in1, in2, sum, carry)
  probe("sum", sum)                               //> sum 0 new-value = false
  probe("carry", carry)                           //> carry 0 new-value = false
  
   in1.setSignal(true)
  run()                                           //> *** simulation started, time = 0 ***
                                                  //| sum 8 new-value = true
  in2.setSignal(true)
  run()                                           //> *** simulation started, time = 8 ***
                                                  //| carry 11 new-value = true
                                                  //| sum 16 new-value = false
}