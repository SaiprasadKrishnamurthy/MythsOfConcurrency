package pumpkin.coffeeshop

import org.scalatest.{ShouldMatchers, FlatSpec}
import BarristaConfig._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by sai on 30/08/2015.
 */
class CoffeeShop extends FlatSpec with ShouldMatchers {

  "Synchronous coffee maker" should "prepare the coffee for the requests sequentially one after the other in a synchronous way " in {
    val coffeeRequests = Seq(CoffeeRequest("Sai", "Mocha", "Large"), CoffeeRequest("Joe", "Cappuchino", "Regular"), CoffeeRequest("Blogg", "Latte", "Regular"))
    val coffees = CoffeeMakerService.makeCoffeeSynchronous(coffeeRequests)
    println("----------- Coffees ready ------------- " + coffees)
  }

  "Async coffee maker" should "prepare the coffee for the requests in an asynchronous way " in {
    val coffeeRequests = Seq(CoffeeRequest("Sai", "Mocha", "Large"), CoffeeRequest("Joe", "Cappuchino", "Regular"), CoffeeRequest("Blogg", "Latte", "Regular"))
    val coffees = CoffeeShop.makeCoffeeAsync(coffeeRequests)
    coffees.onComplete(println)
    println("----------- Coffees ready ------------- ")
    Await.ready(coffees, Duration.Inf)
  }

  "NonBlocking coffee maker" should "prepare the coffee for the requests in a non-blocking way end-to-end " in {
    val coffeeRequests = Seq(CoffeeRequest("Sai", "Mocha", "Large"), CoffeeRequest("Joe", "Cappuchino", "Regular"), CoffeeRequest("Blogg", "Latte", "Regular"))
    val coffees = CoffeeShop.makeCoffeeNonBlocking(coffeeRequests)
    coffees.onComplete(println)
    println("----------- Coffees ready ------------- ")
    Await.ready(coffees, Duration.Inf)
  }


}
