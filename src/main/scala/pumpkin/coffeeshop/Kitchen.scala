package pumpkin.coffeeshop

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

import pumpkin.coffeeshop.BarristaConfig._

import scala.concurrent.Future
import scala.concurrent.duration.Duration


/**
 * Created by sai on 30/08/2015.
 */
object Kitchen {

  val df = new SimpleDateFormat("mm:ss");

  private[this] def boilWater(coffeeRequest: CoffeeRequest): Water = {
    println(s"\t[${df.format(System.currentTimeMillis())}] ${Thread.currentThread().getName} STARTED Boiling water for coffee request: ${coffeeRequest}")
    Thread.sleep(1000)
    println(s"\t[${df.format(System.currentTimeMillis())}]${Thread.currentThread().getName} FINISHED Boiling water for coffee request: ${coffeeRequest}")
    Water()
  }

  private[this] def boilMilk(coffeeRequest: CoffeeRequest): Milk = {
    println(s"\t[${df.format(System.currentTimeMillis())}]${Thread.currentThread().getName} STARTED Boiling milk for coffee request: ${coffeeRequest}")
    Thread.sleep(1000)
    println(s"\t[${df.format(System.currentTimeMillis())}]${Thread.currentThread().getName} FINISHED Boiling milk for coffee request: ${coffeeRequest}")
    Milk()
  }

  def prepareCoffeeSynchronous(coffeeRequest: CoffeeRequest): Coffee = {
    println(s"SYNCHRONOUS MODE: STARTED Preparing coffee for coffee request: ${coffeeRequest}")
    boilWater(coffeeRequest)
    boilMilk(coffeeRequest)
    println(s"SYNCHRONOUS MODE: FINISHED Preparing coffee for coffee request: ${coffeeRequest}\n")
    Coffee(coffeeRequest.customerName, coffeeRequest.coffee, coffeeRequest.size)
  }

  def prepareCoffeeAsync(coffeeRequest: CoffeeRequest): Future[Coffee] = {
    println(s"ASYNC STARTED Preparing coffee for coffee request: ${coffeeRequest}")
    Future {
      val waterF =
        boilWater(coffeeRequest)

      val milkF =
        boilMilk(coffeeRequest)

      println(s"ASYNC FINISHED Preparing coffee for coffee request: ${coffeeRequest}\n")
      Coffee(coffeeRequest.customerName, coffeeRequest.coffee, coffeeRequest.size)
    }

  }

  def prepareCoffeeNonBlocking(coffeeRequest: CoffeeRequest): Future[Coffee] = {
    println(s"NON BLOCKING STARTED Preparing coffee for coffee request: ${coffeeRequest}")

    val waterF = Future {
      boilWater(coffeeRequest)
    }
    val milkF = Future {
      boilMilk(coffeeRequest)
    }
    println(s"NON BLOCKING FINISHED Preparing coffee for coffee request: ${coffeeRequest}\n")
    Future {
      Coffee(coffeeRequest.customerName, coffeeRequest.coffee, coffeeRequest.size)
    }
  }
}
