package pumpkin.coffeeshop

import scala.concurrent.Future
import BarristaConfig._

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
