/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.matlab.nonlinear

import com.stulsoft.matlab.exception.{MatlabException, SolutionException}
import com.typesafe.scalalogging.StrictLogging

class EnumerationMethod(left: Double, right: Double, eps: Double, f: Double => Double) extends SolutionMethod(left, right, eps, f) with StrictLogging:
  private var x = left

  override def validateArguments(left: Double, right: Double, eps: Double)(f: Double => Double): Option[MatlabException] =
    if left >= right then Some(new SolutionException("Left value is not less than right value"))
    else if eps < 0 then Some(new SolutionException("The eps is negative"))
    else if (f(left) * f(right)) > 0 then Some(new SolutionException("No solution found in the specified interval"))
    else None

  override def nextX(): Option[Double] =
    if x <= right then
      x += eps
      Some(x)
    else None

end EnumerationMethod

