package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    k <- arbitrary[Int]
    m <- oneOf(const(empty), genHeap)
  } yield insert(k, m)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)
  
  def getOrderedList(h: H): List[Int] = {
    if(isEmpty(h)) Nil
    else findMin(h) :: getOrderedList(deleteMin(h))
  }
  
  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }
  
  property("min2Empty") = forAll { (x: Int, y: Int) =>
    val h = insert(y, insert(x, empty))
    findMin(h) == math.min(x, y)
  }
  
  property("deleteEmpty") = forAll { a: Int =>
    val h = insert(a, empty)
    deleteMin(h) == empty
  }
  
  property("orderedHeap") = forAll { (h: H) =>
    if (isEmpty(h)) true
    else {
      val list = getOrderedList(h)
      list == list.sorted
    }
  }
  
  property("meldMin") = forAll { (h1: H, h2: H) =>
    val h = meld(h1, h2)
    val min = findMin(h)
    findMin(h1) == min || findMin(h2) == min 
  }
  
  property("myProp") = forAll { (h1: H, h2: H) =>
    val min1 = findMin(h1)
    val min2 = findMin(h2)
    
    if(min1 > min2){
      findMin(deleteMin(meld(h1, h2))) == findMin(meld(deleteMin(h2),h1))
    } else {
      findMin(deleteMin(meld(h1, h2))) == findMin(meld(deleteMin(h1),h2))
    }
  }
}
