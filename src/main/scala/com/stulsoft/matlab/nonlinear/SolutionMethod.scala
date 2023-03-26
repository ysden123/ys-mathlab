/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.matlab.nonlinear

import com.stulsoft.matlab.exception.{MatlabException, SolutionException}

import scala.util.Try

trait SolutionMethod(left: Double, right: Double, eps: Double,f: Double => Double):
  def validateArguments(left: Double, right: Double, eps: Double)(f: Double => Double): Option[MatlabException]

  def nextX(): Option[Double]

  def solve(): Try[Double] =
    Try {
      validateArguments(left, right, eps)(f) match
        case Some(exception) => throw exception
        case None =>
          var x: Double = nextX() match
            case Some(nextX) => nextX
            case _ => throw new SolutionException("No solution found in the specified interval (all available values of an x were used)")
          while Math.abs(f(x)) > eps do
            x = nextX() match
              case Some(nextX) => nextX
              case _ => throw new SolutionException("No solution found in the specified interval (no available value of x was found)")

          if Math.abs(f(x)) > eps then throw new SolutionException("No solution found in the specified interval")
          x
    }