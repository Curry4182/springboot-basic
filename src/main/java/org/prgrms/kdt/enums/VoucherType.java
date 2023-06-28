package org.prgrms.kdt.enums;

import java.util.Arrays;

public enum VoucherType {
	FixedAmountVoucher(1),
	PercentDiscountVoucher(2);

	private final static String UN_SUPPORTED_VOUCHER_MESSAGE = "잘 못된 입력 입니다.";
	private final int voucherIdx;

	VoucherType(int voucherIdx) {
		this.voucherIdx = voucherIdx;
	}

	public static VoucherType valueOf(int voucherIdx) {
		return Arrays.stream(VoucherType.values())
			.filter(voucher -> voucher.voucherIdx == voucherIdx)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(UN_SUPPORTED_VOUCHER_MESSAGE));
	}

	public int getVoucherIdx() {
		return voucherIdx;
	}
}
