package com.poc.code.practices.demo.MDC;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class AccountServiceImplTest {

    @Test
    void transferBulk() throws IOException {
        try (AccountService accountService = new AccountServiceImpl()) {
            List<TransferRequest> transferRequestList = List.of(
                    TransferRequest.of("Deepak", "Kumar", 1000),
                    TransferRequest.of("P1", "p2", 1000),
                    TransferRequest.of("p3", "p4", 1000),
                    TransferRequest.of("p1", "p2", 5000),
                    TransferRequest.of("p2", "p1", 6000),
                    TransferRequest.of("p8", "p1", 100),
                    TransferRequest.of("p7", "p3", 9000)
            );
            accountService.transferBulk(transferRequestList);
        }
    }
}