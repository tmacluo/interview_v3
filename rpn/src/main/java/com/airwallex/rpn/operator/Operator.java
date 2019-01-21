package com.airwallex.rpn.operator;

import java.math.BigDecimal;

/**
 * Simple interface to specify calculation methods allowed by the calculator
 */
public interface Operator {
	BigDecimal calc(BigDecimal a, BigDecimal b);
}
