package com.poc.code.practices.demo.MDC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class VerboseBalanceTransferService extends BalanceTransferService {
    private static final Logger log = LoggerFactory.getLogger(VerboseBalanceTransferService.class);

    @Override
    public void beforeTransfer() {
        log.info("Transfer being initiated for transfer request: {}", MDC.get(TransferConstants.TRANSFER_REQUEST_CONTEXT));
    }

    @Override
    public void afterTransfer() {
        log.info("Transfer completed for transfer request: {}", MDC.get(TransferConstants.TRANSFER_REQUEST_CONTEXT));
    }
}
