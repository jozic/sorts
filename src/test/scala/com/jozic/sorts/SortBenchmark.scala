package com.jozic.sorts

import scala.language.implicitConversions
import org.scalameter.{Parameters, Gen, PerformanceTest}
import org.scalacheck.{Gen => SGen}
import collection.Iterator
import org.scalacheck.Gen.Params

object SortBenchmark extends PerformanceTest.Quickbenchmark {

  val sizes: Gen[Int] = Gen.range("size")(30, 150, 30)

  def randomArrays = arrays

  private def arrays: Gen[Array[Int]] = for {
    size <- sizes
    x = for (_ <- 0 to size) yield (0 + math.abs(scala.util.Random.nextInt % (size + 1)))
  } yield x.toArray


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


  private implicit def scalacheck2scalameterGen[A](gen: SGen[A]): Gen[A] = new Gen[A] {
    def generate(params: Parameters): A = gen(params[Params](gen.label)).get

    def warmupset: Iterator[A] = Iterator.single(gen.sample.get)

    def dataset: Iterator[Parameters] = Iterator.continually(Parameters(gen.label -> SGen.Params()))
  }

  private def arrays2: Gen[Array[Int]] = for {
    size <- sizes
    randomGen = SGen.choose(0, size)
    a <- SGen.containerOfN[Array, Int](size, randomGen) :| "random ints"
  } yield a

}