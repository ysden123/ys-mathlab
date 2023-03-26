/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.matlab.exception

import org.scalatest.flatspec.AnyFlatSpec

class SolutionExceptionTest extends AnyFlatSpec:
  "SolutionException" should "throw exception" in {
    assertThrows[SolutionException]{
      throw new SolutionException("Error message")
    }

    val caught = intercept[SolutionException]{
      throw new SolutionException("Error message")
    }
    assert("Error message" == caught.getMessage)
  }