/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.matlab.analysis

import com.stulsoft.matlab.nonlinear.{BisectionMethod, EnumerationMethod}

object AnalyzeNonlinearMethods:
  private def testBisectionMethod(): Unit =
    println("==>testBisectionMethod")

    val functionUsageMetrics = FunctionUsageMetrics()

    def f(x: Double): Double =
      functionUsageMetrics.increaseCallCount()
      Math.sin(x)

    val method = new BisectionMethod(-0.5, 0.5, 0.005, f)
    method.solve()
    println(functionUsageMetrics)


  private def testEnumerationMethod(): Unit =
    println("==>testEnumerationMethod")

    val functionUsageMetrics = FunctionUsageMetrics()

    def f(x: Double): Double =
      functionUsageMetrics.increaseCallCount()
      Math.sin(x)

    val method = new EnumerationMethod(-0.5, 0.5, 0.005, f)

    method.solve()
    println(functionUsageMetrics)

  def main(args: Array[String]): Unit =
    println("==>main")
    testBisectionMethod()
    testEnumerationMethod()
