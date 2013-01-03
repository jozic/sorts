package com.jozic.sorts

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class SortSpec extends FunSpec with MustMatchers {

  def assertSort[A: Ordering](sorter: Sort, array: Array[A]) {
    assert(isSorted(sorter.sort(array)) === true)
  }

  def isSorted[A: Ordering](array: Array[A]): Boolean = {
    val ord = Ordering[A]
    import ord._
    var i = 0
    while (i != array.length - 1) {
      if (array(i) > array(i + 1)) return false
      i += 1
    }
    true
  }

  def testCorrectness(sorter: Sort) {
    assertSort(sorter, Array(1, 3, 2, 5, 4))
    assertSort(sorter, Array(1, 2, 3, 4, 5))
    assertSort(sorter, Array(5, 4, 3, 2, 1))
    assertSort(sorter, Array(5, 1, 2, 3, 4))
    assertSort(sorter, Array(2, 4, 6, 1, 3, 5))
  }

  def assertStable(sorter: Sort, array: Array[StableCheck]) {
    isStable(sorter.sort(array)) must be(true)
  }

  def assertNotStable(sorter: Sort, array: Array[StableCheck]) {
    isStable(sorter.sort(array)) must be(false)
  }

  def isStable(array: Array[StableCheck]): Boolean = {
    array.groupBy(_.value) map (g => g._2 map (_.check)) foreach {
      a => if (!isSorted(a)) return false
    }
    true
  }

  def assertStable(sorter: Sort) {
    assertStable(sorter, Array(s(1, 1), s(1, 2), s(5, 1), s(5, 2), s(3, 1), s(3, 2)))
    assertStable(sorter, Array(s(3, 1), s(3, 2), s(1, 1), s(5, 1)))
    assertStable(sorter, Array(s(5, 1), s(5, 2), s(3, 1), s(3, 2), s(1, 1), s(1, 2)))
  }

  def assertNotStable(sorter: Sort) {
    assertNotStable(sorter, Array(s(1, 1), s(1, 2), s(5, 1), s(5, 2), s(3, 1), s(3, 2)))
    assertNotStable(sorter, Array(s(3, 1), s(3, 2), s(1, 1), s(5, 1)))
    assertNotStable(sorter, Array(s(5, 1), s(5, 2), s(3, 1), s(3, 2), s(1, 1), s(1, 2)))
  }

  case class StableCheck(value: Int, check: Int)

  def s(value: Int, check: Int) = StableCheck(value, check)

  implicit val StableCheckOrdering = new Ordering[StableCheck] {
    def compare(x: SortSpec.this.type#StableCheck, y: SortSpec.this.type#StableCheck) = x.value - y.value
  }

  describe("Bubble sort") {
    it("should sort correctly") {
      testCorrectness(BubbleSort)
    }
    it("should be stable") {
      assertStable(BubbleSort)
    }
  }

  describe("Selection sort") {
    it("should sort correctly") {
      testCorrectness(SelectionSort)
    }
    it("should NOT be stable") {
      assertNotStable(SelectionSort, Array(s(3, 1), s(3, 2), s(1, 1), s(5, 1)))
    }
  }

  describe("Insertion sort") {
    it("should sort correctly") {
      testCorrectness(InsertionSort)
    }
    it("should be stable") {
      assertStable(InsertionSort)
    }
  }

  describe("Merge sort") {
    it("should sort correctly") {
      testCorrectness(MergeSort)
    }
    it("should be stable") {
      assertStable(MergeSort)
    }
  }
}