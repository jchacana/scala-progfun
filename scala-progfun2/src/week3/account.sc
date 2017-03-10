package week3

object account {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val acct = new BankAccount                      //> acct  : week3.BankAccount = week3.BankAccount@6a5fc7f7
  
  acct deposit 50
  acct withdraw 20                                //> res0: Int = 30
  acct withdraw 20                                //> res1: Int = 10
  acct withdraw 15                                //> java.lang.Error: Insufficent funds
                                                  //| 	at week3.BankAccount.withdraw(BankAccount.scala:14)
                                                  //| 	at week3.account$$anonfun$main$1.apply$mcV$sp(week3.account.scala:11)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.account$.main(week3.account.scala:3)
                                                  //| 	at week3.account.main(week3.account.scala)
}