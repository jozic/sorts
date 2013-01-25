package com.jozic.sorts

import annotation.tailrec

trait Sort {

  def sort[A: Ordering](a: Array[A]): Array[A]

  def swap[A](a: Array[A], i: Int, j: Int) {
    if (i != j) {
      val t = a(i)
      a(i) = a(j)
      a(j) = t
    }
  }

  def debug[A](a: Array[A]) {
    println(a.mkString(" "))
  }

  def debugEnter[A](a: Array[A]) {
    println(s"================== sorting: ${a.mkString(" ")}")
  }
}

object BubbleSort extends Sort {

  def sort[A: Ordering](a: Array[A]) = {
    val ord = Ordering[A]
    import ord._
    for (i <- 0 until a.length)
      for (j <- 0 until a.length - (i + 1))
        if (a(j) > a(j + 1)) swap(a, j, j + 1)

    a
  }
}

object SelectionSort extends Sort {
  def sort[A: Ordering](a: Array[A]) = {
    val ord = Ordering[A]
    import ord._
    for (i <- 0 until a.length) {
      var minIdx = i
      for (j <- i + 1 until a.length) {
        if (a(j) < a(minIdx)) minIdx = j
      }
      swap(a, i, minIdx)
    }
    a
  }
}

object InsertionSort extends Sort {
  def sort[A: Ordering](a: Array[A]) = {
    val ord = Ordering[A]
    import ord._
    for (i <- 1 until a.length) {
      var j = i
      while (j >= 1 && a(j) < a(j - 1)) {
        swap(a, j, j - 1)
        j -= 1
      }
    }
    a
  }
}

object MergeSort extends Sort {
  def sort[A: Ordering](a: Array[A]) = {
    val ord = Ordering[A]
    import ord._
    val tmp = new Array[Any](a.length).asInstanceOf[Array[A]]

    def merge(l: Int, m: Int, h: Int) {
      var i = l
      var j = m + 1
      var k = l
      for (z <- l to h) tmp(z) = a(z)

      while (i <= m && j <= h) {
        if (tmp(i) <= tmp(j)) {
          a(k) = tmp(i)
          i += 1
        } else {
          a(k) = tmp(j)
          j += 1
        }
        k += 1
      }
      while (i <= m) {
        a(k) = tmp(i)
        i += 1
        k += 1
      }
      while (j <= h) {
        a(k) = tmp(j)
        j += 1
        k += 1
      }
    }

    def sort0(l: Int, h: Int) {
      if (l < h) {
        val m = (l + h) / 2
        sort0(l, m)
        sort0(m + 1, h)
        if (a(m) > a(m + 1))
          merge(l, m, h)
      }
    }

    sort0(0, a.length - 1)
    a
  }

}

object HeapSort extends Sort {

  def sort[A: Ordering](a: Array[A]) = {
    val ord = Ordering[A]
    import ord._

    @tailrec
    def sink(k: Int, n: Int) {
      val leftChildIndex = 2 * k + 1
      if (leftChildIndex < n) {
        val rightChildIndex = leftChildIndex + 1
        val maxChildIndex = if (rightChildIndex < n && a(leftChildIndex) < a(rightChildIndex)) rightChildIndex else leftChildIndex
        if (a(k) < a(maxChildIndex)) {
          swap(a, k, maxChildIndex)
          sink(maxChildIndex, n)
        }
      }
    }

    for (i <- a.length / 2 to 0 by -1) sink(i, a.length)
    for (n <- a.length - 1 to 1 by -1) {
      swap(a, 0, n)
      sink(0, n)
    }
    a
  }
}