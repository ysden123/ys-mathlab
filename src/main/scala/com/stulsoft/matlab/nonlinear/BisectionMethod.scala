/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.matlab.nonlinear

import com.stulsoft.matlab.exception.{MatlabException, SolutionException}
import com.stulsoft.matlab.nonlinear.SolutionMethod
import com.typesafe.scalalogging.StrictLogging

class BisectionMethod(left: Double, right: Double, eps: Double, f: Double => Double) extends SolutionMethod(left, right, eps, f) with StrictLogging:
  private var x: Double = _
  private var currentLeft:Double = _
  private var currentRight:Double = _
  private var firstIteration = true

  override def validateArguments(left: Double, right: Double, eps: Double)(f: Double => Double): Option[MatlabException] =
    if left >= right then Some(new SolutionException("Left value is not less than right value"))
    else if eps < 0 then Some(new SolutionException("The eps is negative"))
    else if (f(left) * f(right)) > 0 then Some(new SolutionException("No solution found on the specified interval"))
    else None

  override def nextX(): Option[Double] =
    if firstIteration then
      x = left
      currentLeft = left
      currentRight = right
      firstIteration = false
      Some(x)
    else
      x = (currentLeft + currentRight) / 2.0
      if f(x) * f(currentLeft) < 0 then
        currentRight = x
      else
        currentLeft = x
      Some(x)
end BisectionMethod

