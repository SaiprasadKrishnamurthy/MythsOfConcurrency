package pumpkin.basics

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService}

/**
 * Created by sai on 30/08/2015.
 */
object Config extends Config(numberOfThreads = 1)

class Config(numberOfThreads: Int) {
  val threadPool = Executors.newScheduledThreadPool(numberOfThreads)
  implicit val executionContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(threadPool)
}