package pumpkin.basics

import pumpkin.basics.Config.executionContext

import scala.concurrent.Future


/**
 * Created by sai on 30/08/2015.
 */
object SquaresService {

  def blocking(n: Seq[Int]) = {
    n.map(SquaresDao.squareBlocking)
  }

  def async(n: Seq[Int]): Future[Seq[Int]] = {
    val resultF = n.map(SquaresDao.squareAsync)
    Future.sequence(resultF)
  }

  def nonBlocking(n: Seq[Int]): Future[Seq[Int]] = {
    val resultF = n.map(SquaresDao.squareNonBlocking)
    Future.sequence(resultF)
  }
}
