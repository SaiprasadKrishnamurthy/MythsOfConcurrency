package pumpkin.basics

import org.scalatest.{FlatSpec, ShouldMatchers}
import pumpkin.basics.Config._

import scala.concurrent.Await
import scala.concurrent.duration.Duration._

/**
 * Created by sai on 30/08/2015.
 */
class SquaresTest extends FlatSpec with ShouldMatchers {

  "blocking squares" should "compute the results sequentially and slowly! " in {
    SquaresService.blocking((1 to 5))
    println("All done")
    println(" ------------------------------END OF TEST-----------------------------------")
  }


  "async squares" should "compute NOT block the main thread and compute the the results asynchronously! " in {
    val resultsF = SquaresService.async((1 to 5))
    println("All done")
    resultsF.onComplete(println)
    Await.ready(resultsF, Inf)
    println(" ------------------------------END OF TEST-----------------------------------")
  }

  "nonBlocking squares" should "compute NOT block any worker threads and compute the the results asynchronously too! " in {
    val resultsF = SquaresService.nonBlocking((1 to 5))
    println("All done")
    resultsF.onComplete(println)
    Await.ready(resultsF, Inf)
    println(" ------------------------------END OF TEST-----------------------------------")
  }

}
