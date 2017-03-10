package week4

object observers {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val a = new BankAccount                         //> a  : week4.BankAccount = week4.BankAccount@3d24753a
  val b = new BankAccount                         //> b  : week4.BankAccount = week4.BankAccount@59a6e353
  val c = new Consolidator(List(a, b))            //> c  : week4.Consolidator = week4.Consolidator@60addb54
  
  c.totalBalance                                  //> res0: Int = 0
  
  a.deposit(20)
  c.totalBalance                                  //> res1: Int = 20
  b.deposit(30)
  c.totalBalance                                  //> res2: Int = 50
}