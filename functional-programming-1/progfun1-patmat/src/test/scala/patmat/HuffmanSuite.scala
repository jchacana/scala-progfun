package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
		
	}

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t2) === 9)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }
  
  test("Times of a unique tree") {
    new TestTrees {
      assert(times(List('a','b','d')).sorted === List(('a', 1), ('b', 1), ('d', 1)).sorted)
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("is not singleton") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(!singleton(leaflist))
  }
  
  test("is singleton") {
    val leaflist = List(Leaf('e', 1))
    assert(singleton(leaflist))
  }
  
  test("is singleton complex") {
    val leaflist = List(Fork(
          Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), 
          Leaf('x',4), List('e','t','x'),7))
    assert(singleton(leaflist))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  
  test("combine until it's singleton") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(until(singleton, combine) (leaflist) === 
      List(Fork(
          Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), 
          Leaf('x',4), List('e','t','x'),7)))
  }
  
  test("create codetree of 'xetxxtx'") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(createCodeTree(string2Chars("xetxxtx")) === 
      Fork(
        Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), 
        Leaf('x',4), List('e','t','x'),7
      )
    )
  }
  
  test("decode 'ab' from bits") {
    new TestTrees {
      assert(decode(t1, List(0,1)) === "ab".toList)
    }
  }
  
  test("decode 'abda' from bits") {
    new TestTrees {
      assert(decode(t2, List(0,0,0,1,1,0,0)) === "abda".toList)
    }
  }
  
  test("decoded secret with frenchCode") {
    new TestTrees {
      assert(decode(frenchCode, secret) === "huffmanestcool".toList)
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("convert codetree to codetable") {
    new TestTrees {
      assert(convert(t2).sortBy(x => x._1) === List(('a', List(0,0)),('b', List(0,1)),('d', List(1))))
    }
  }

  test("quick encode of 'abda'") {
    new TestTrees {
      assert(quickEncode(t2)("abda".toList) === List(0,0,0,1,1,0,0))
    }
  }
}
