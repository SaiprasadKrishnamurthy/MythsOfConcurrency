package scratchpad

import api.git.GitDriver

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Created by sai on 26/08/2015.
 */
object FutureExample extends App {

  case class Water(temp: Int)

  case class Milk(temp: Int)


  def heatWaterFor(minutes: Int, water: Water) = Future {
    Thread.sleep(1000)
    Water(82)
  }

  def boilMilkFor(minutes: Int, milk: Milk) = Future {
    Thread.sleep(1000)
    Milk(90)
  }

  def frothMilk(hotwater: Water, hotmilk: Milk) = Future {
    Thread.sleep(1000)
    hotmilk
  }

  val start = System.currentTimeMillis()
  val boilWater = heatWaterFor(10, Water(10))
  val boilMilk = boilMilkFor(5, Milk(10))

  val milkMaker = for {
    water <- boilWater
    milk <- boilMilk
    frothed = frothMilk(water, milk)
    hotMilk <- frothed
  } yield (hotMilk)

  Await.ready(milkMaker, Duration.Inf)
  val end = System.currentTimeMillis() - start
  println(milkMaker.value + " , Time taken: " + ((end / 1000)) + " seconds.")
}
