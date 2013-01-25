package com.jozic.sorts

import scala.language.implicitConversions
import org.scalameter.{Parameters, Gen, PerformanceTest}
import org.scalacheck.{Gen => SGen}
import collection.Iterator
import org.scalacheck.Gen.Params
import org.scalameter.Executor.Measurer

object SortBenchmark extends PerformanceTest.Quickbenchmark {

  override def measurer = new Measurer.Default with Measurer.PeriodicReinstantiation {
    override val defaultFrequency = 1
  }

//  val sizes: Gen[Int] = Gen.range("size")(30, 150, 30)
  val sizes: Gen[Int] = Gen.range("size")(3000, 15000, 3000)

  def randomArrays = arrays

  performance of "BubbleSort" in {
    using(randomArrays) in {
      BubbleSort.sort(_)
    }
  }

  performance of "SelectionSort" in {
    using(randomArrays) in {
      SelectionSort.sort(_)
    }
  }

  performance of "InsertionSort" in {
    using(randomArrays) in {
      InsertionSort.sort(_)
    }
  }

  performance of "MergeSort" in {
    using(randomArrays) in {
      MergeSort.sort(_)
    }
  }

  performance of "HeapSort" in {
    using(randomArrays) in {
      HeapSort.sort(_)
    }
  }

  private implicit def scalacheck2scalameterGen[A](gen: SGen[A]): Gen[A] = new Gen[A] {
    def generate(params: Parameters): A = gen(params[Params](gen.label)).get

    def warmupset: Iterator[A] = Iterator.single(gen.sample.get)

    def dataset: Iterator[Parameters] = Iterator.single(Parameters(gen.label -> SGen.Params()))
  }

  private def arrays: Gen[Array[Int]] = for {
    size <- sizes
    randomGen = SGen.choose(0, size)
    array <- SGen.containerOfN[Array, Int](size, randomGen) :| "random ints"
  } yield array


  def printGen[A](gen: Gen[A]) {
    forceGen(gen).foreach(println)
  }

  def forceGen[A](gen: Gen[A]) = {
    (for (p <- gen.dataset) yield gen.generate(p)).toList
  }
}