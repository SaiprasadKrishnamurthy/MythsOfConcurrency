package pumpkin.coffeeshop

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, Promise}
import scala.util.Try

/**
 * Created by sai on 30/08/2015.
 */
object BarristaTaskScheduler {

  def schedule[T](delay: Duration)(block: => T): Future[T] = {
    val promise = Promise[T]()

    // Schedule it to run later.
    BarristaConfig.baristaPool.schedule(
      new Runnable {
        override def run(): Unit = promise.complete(Try(block))
      },
      delay.length,
      delay.unit
    )
    promise.future
  }

}
