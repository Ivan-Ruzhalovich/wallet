package com.ivan.ruzhalovich.waletrest;

import java.util.UUID;

public class WalletRequest {
    private UUID valletId;
    private String operationType;
    private long amount;

    public WalletRequest(UUID valletId, String operationType, long amount) {
        this.valletId = valletId;
        this.operationType = operationType;
        this.amount = amount;
    }

    public WalletRequest() {
    }

    public UUID getValletId() {
        return valletId;
    }

    public void setValletId(UUID valletId) {
        this.valletId = valletId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
