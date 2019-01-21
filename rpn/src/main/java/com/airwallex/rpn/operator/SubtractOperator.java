package com.airwallex.rpn.operator;

import java.math.BigDecimal;

public class SubtractOperator implements Operator{

	public BigDecimal calc(BigDecimal a, BigDecimal b) {
		return b.subtract(a);
	}

}
