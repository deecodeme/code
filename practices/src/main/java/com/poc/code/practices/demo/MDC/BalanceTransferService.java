package com.poc.code.practices.demo.MDC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BalanceTransferService {
    private static final Logger log = LoggerFactory.getLogger(BalanceTransferService.class);

    public void transfer(TransferRequest transferRequest) {
        beforeTransfer();
        log.info("Transfer in progress for the transfer request: {}", transferRequest);
        afterTransfer();
    }

    public abstract void beforeTransfer();

    public abstract void afterTransfer();
}
