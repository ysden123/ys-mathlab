/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.matlab.nonlinear

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success}

class EnumerationMethodTest extends AnyFlatSpec {
  "EnumerationMethod" should "have solution" in {
    val f = (x: Double) => Math.sin(x)
    val method:SolutionMethod = new EnumerationMethod(-0.5, 0.5, 0.005, f)
    method.solve() match
      case Success(x) =>
        succeed
      case Failure(_) =>
        fail("A solution is expected")
  }
  it should "gave solution 2" in {
    val f = (x: Double) => x * 2.0 + 4.0
    val method: SolutionMethod = new EnumerationMethod(-3.0, 10.0, 0.005, f)
    method.solve() match
      case Success(x) =>
        println(s"x=$x")
        succeed
      case Failure(exception) =>
        fail("A solution is expected: " + exception.getMessage)
  }

  it should "throw exception (invalid arguments" in {
    val f = (x: Double) => x * 2.0 + 4.0
    val method: SolutionMethod = new EnumerationMethod(10.0, -3.0, 0.005, f)
    method.solve() match
      case Success(_) =>
        fail("Exception is expected")
        succeed
      case Failure(_) =>
        succeed
  }

  it should "throw exception (wrong interval" in {
    val f = (x: Double) => x * 2.0 + 4.0
    val method: SolutionMethod = new EnumerationMethod(-1.5, 10.0, 0.005, f)
    method.solve() match
      case Success(_) =>
        fail("Exception is expected")
        succeed
      case Failure(_) =>
        succeed
  }

}
