package pumpkin.coffeeshop

import java.text.SimpleDateFormat
import java.util.concurrent.{ThreadFactory, Executors}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService}
import scala.util.Random

/**
 * Created by sai on 30/08/2015.
 */
object BarristaConfig extends BarristaConfig(numberOfBaristas = 2)

class BarristaConfig(numberOfBaristas: Int) {
  val random = new Random()

  val baristaPool = Executors.newScheduledThreadPool(numberOfBaristas, new ThreadFactory {
    override def newThread(r: Runnable): Thread = {
      new Thread(r, s"(Barrista: ${random.alphanumeric.take(3).mkString("")}) ")
    }
  })

  implicit val executionContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(baristaPool)
}