package org.prgrms.kdt.common.exception;

import org.prgrms.kdt.common.response.ErrorResponse;
import org.prgrms.kdt.model.repository.file.FileVoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(FileVoucherRepository.class);

	@ExceptionHandler(CommonRuntimeException.class)
	protected ErrorResponse handleRuntimeException(CommonRuntimeException ex) {
		logger.error("Common Runtime Exception", ex);
		return new ErrorResponse(ex.getErrorCode());
	}
}
