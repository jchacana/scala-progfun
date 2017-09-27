package reductions

import scala.annotation._
import org.scalameter._
import common._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true) withWarmer (new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /**
   * Returns `true` if the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    balance2(chars) == (0, 0)
  }

  def balance2(chars: Array[Char]): (Int, Int) = {
    var accL = 0
    var accR = 0
    for (c <- chars) {
      if (c == '(') accL = accL + 1
      else if (c == ')') {
        if (accL > 0) accL = accL - 1
        else accR = accR + 1
      }
    }
    (accL, accR)
  }

  /**
   * Returns `true` if the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def balance(chars: Array[Char]): (Int, Int) = {
      var accL = 0
      var accR = 0
      for (c <- chars) {
        if (c == '(') accL = accL + 1
        else if (c == ')') {
          if (accL > 0) accL = accL - 1
          else accR = accR + 1
        }
      }
      (accL, accR)
    }

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int): (Int, Int) = {
      val m: Int = Math.min(idx, arg2)
      (idx + arg1 - m, until + arg1 - m)
    }

    def reduce(from: Int, until: Int): (Int, Int) = {
      if (until - from <= threshold) {
        balance(chars)
      } else {
        val mid = from + (until - from) / 2
        val (left, right) = parallel(
          reduce(from, mid),
          reduce(mid, until)
        )
        traverse(left._1, left._2, right._1, right._2)
      }
    }

    reduce(0, chars.length) == (0, 0)
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
