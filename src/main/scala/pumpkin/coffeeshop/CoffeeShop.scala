package pumpkin.coffeeshop

import java.util.concurrent.{TimeUnit, Callable}

import scala.concurrent.Future
import BarristaConfig._

import scala.concurrent.duration.Duration

/**
 * Created by sai on 30/08/2015.
 */
object CoffeeShop {

  /**
   * Prepare coffee requests one by one in a sequential, blocking fashion.
   * @param coffeeRequests
   * @return
   */
  def makeCoffeeSynchronous(coffeeRequests: Seq[CoffeeRequest]): Seq[Coffee] = {
    coffeeRequests.map(Kitchen.prepareCoffeeSynchronous)
  }

  /**
   * A Naive implementation to process coffees concurrently.
   * @param coffeeRequests
   * @return
   */
  def makeCoffeeConcurrentlyNaive(coffeeRequests: Seq[CoffeeRequest]): Seq[Coffee] = {
    val coffeeMakingTasks = coffeeRequests.map(coffeeRequest => new Callable[Coffee] {
      override def call() = Kitchen.prepareCoffeeSynchronous(coffeeRequest)
    })
    val coffees = coffeeMakingTasks.map(task => BarristaConfig.baristaPool.schedule(task, 1, TimeUnit.MILLISECONDS))
    coffees.map(coffee => coffee.get)
  }

  /**
   * Prepare coffee requests in an async fashion.
   * @param coffeeRequests
   * @return
   */
  def makeCoffeeAsync(coffeeRequests: Seq[CoffeeRequest]): Future[Seq[Coffee]] = {
    val coffees = coffeeRequests.map(Kitchen.prepareCoffeeAsync)
    Future.sequence(coffees)
  }

  /**
   * Prepare coffee requests in an async fashion.
   * @param coffeeRequests
   * @return
   */
  def makeCoffeeNonBlocking(coffeeRequests: Seq[CoffeeRequest]): Future[Seq[Coffee]] = {
    val coffees = coffeeRequests.map(Kitchen.prepareCoffeeNonBlocking)
    Future.sequence(coffees)
  }
}
