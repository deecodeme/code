package com.poc.code.practices.demo.MDC;

import java.io.Closeable;
import java.util.List;

public interface AccountService extends Closeable {
    void transferBulk(List<TransferRequest> transferRequestList);
}
