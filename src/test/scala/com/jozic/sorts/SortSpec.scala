package com.jozic.sorts

import org.scalatest.FunSpec

class SortSpec extends FunSpec {

  def testCorrectness(sorter: Sort) {
    assert(sorter.sort(Array(1, 3, 2, 5, 4)) === Array(1, 2, 3, 4, 5))
    assert(sorter.sort(Array(1, 2, 3, 4, 5)) === Array(1, 2, 3, 4, 5))
    assert(sorter.sort(Array(5, 4, 3, 2, 1)) === Array(1, 2, 3, 4, 5))
    assert(sorter.sort(Array(5, 1, 2, 3, 4)) === Array(1, 2, 3, 4, 5))
    assert(sorter.sort(Array(2, 4, 6, 1, 3, 5)) === Array(1, 2, 3, 4, 5, 6))
  }

  describe("Bubble sort") {
    it("should sort correctly") {
      testCorrectness(BubbleSort)
    }
  }

  describe("Selection sort") {
    it("should sort correctly") {
      testCorrectness(SelectionSort)
    }
  }

  describe("Insertion sort") {
    it("should sort correctly") {
      testCorrectness(InsertionSort)
    }
  }

  describe("Merge sort") {
    it("should sort correctly") {
      testCorrectness(MergeSort)
    }
  }

}
