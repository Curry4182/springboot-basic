package org.prgrms.kdt.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.prgrms.kdt.enums.VoucherType;
import org.prgrms.kdt.model.dto.VoucherDTO;
import org.prgrms.kdt.model.dto.VoucherRequest;
import org.prgrms.kdt.model.dto.VoucherResponse;
import org.prgrms.kdt.service.VoucherService;
import org.prgrms.kdt.util.VoucherFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VoucherController {
	private final VoucherService voucherService;

	private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);

	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}

	@GetMapping("/vouchers")
	public String viewVouchersPage(Model model) {
		List<VoucherResponse> vouchers = voucherService
			.getVouchers()
			.stream().map(voucherDTO -> voucherDTO.toResponse())
			.collect(Collectors.toList());

		model.addAttribute("vouchers", vouchers);
		return "vouchers";
	}

	@GetMapping("/voucher/new")
	public String viewNewVoucherPage() {
		return "new-vouchers";
	}

	@PostMapping("/voucher/new")
	public String addNewVoucher(VoucherRequest voucherRequest) {
		int amount = voucherRequest.amount();
		int voucherTypeIdx = voucherRequest.voucherTypeIdx();

		VoucherDTO newVoucherDTO = VoucherFactory.getVoucherDTO(amount, VoucherType.valueOf(voucherTypeIdx));
		voucherService.createVoucher(newVoucherDTO);
		return "redirect:/vouchers";
	}

	@GetMapping("/vouchers/details/{voucherId}")
	public String findVoucher(@PathVariable("voucherId") Long voucherId, Model model) {
		VoucherDTO maybeVoucher = voucherService.findVoucherById(voucherId);
		model.addAttribute("voucher", maybeVoucher);
		return "voucher-details";
	}

	@GetMapping("/vouchers/delete/{voucherId}")
	public String deleteVoucherPage(@PathVariable("voucherId") Long voucherId, Model model) {
		voucherService.deleteVoucherById(voucherId);
		return "redirect:/vouchers";
	}
}
