package org.prgrms.kdt.model;

public class FixedAmount implements Amount {

	private final int amount;

	public FixedAmount(int amount) {
		if (!validate(amount)) {
			throw new IllegalArgumentException("FixedAmount의 할인 값은 1이상 이어야 합니다.");
		}

		this.amount = amount;
	}

	@Override
	public boolean validate(int amount) {
		return amount >= 1;
	}

	@Override
	public int getAmount() {
		return this.amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		FixedAmount that = (FixedAmount)o;

		return amount == that.amount;
	}

	@Override
	public int hashCode() {
		return amount;
	}
}
