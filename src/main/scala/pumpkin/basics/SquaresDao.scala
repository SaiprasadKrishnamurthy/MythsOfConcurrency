package pumpkin.basics

import java.util.concurrent.TimeUnit

import pumpkin.basics.Config.executionContext

import scala.concurrent.Future
import scala.concurrent.duration.Duration

/**
 * Created by sai on 30/08/2015.
 */
object SquaresDao {

  def squareBlocking(n: Int): Int = {
    println(s"Begin to square (Blocking): ${n}")
    Thread.sleep(1000)
    val res = n * n
    println(s"Finished squaring (Blocking): ${n}\n")
    res
  }

  def squareAsync(n: Int): Future[Int] = {
    Future {
      println(s"Begin to square (Async): ${n}")
      Thread.sleep(1000)
      val res = n * n
      println(s"Finished squaring (Async): ${n}\n")
      res
    }
  }

  def squareNonBlocking(n: Int): Future[Int] = {
    Scheduler.schedule(Duration.create(1, TimeUnit.MILLISECONDS)) {
      println(s"Begin to square (Non-Blocking): ${n}")
      val res = n * n
      println(s"Finished squaring (Non-Blocking): ${n}\n")
      res
    }
  }
}
