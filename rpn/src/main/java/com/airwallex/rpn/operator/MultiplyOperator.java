package com.airwallex.rpn.operator;

import java.math.BigDecimal;

public class MultiplyOperator implements Operator{

	public BigDecimal calc(BigDecimal a, BigDecimal b) {
		return b.multiply(a);
	}

}
