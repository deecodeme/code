package com.poc.code.practices.demo.MDC;

import org.slf4j.MDC;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountServiceImpl implements AccountService {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    private static final BalanceTransferService balanceTransferService = new VerboseBalanceTransferService();

    @Override
    public void transferBulk(List<TransferRequest> transferRequestList) {
        transferRequestList.forEach(transferRequest -> executor.execute(() -> {
            MDC.clear();
            MDC.put(TransferConstants.TRANSFER_REQUEST_CONTEXT, transferRequest.toString());
            balanceTransferService.transfer(transferRequest);
        }));
    }

    @Override
    public void close() throws IOException {
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
