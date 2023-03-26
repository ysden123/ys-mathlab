/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.matlab.analysis

class FunctionUsageMetrics:
  private var callCount: Int = 0

  def increaseCallCount(): Unit =
    callCount += 1

  override def toString: String = "FunctionUsageMetrics{"
    + s"callCount=$callCount"
    + "}"

  def getCallCount: Int = callCount
  
  
