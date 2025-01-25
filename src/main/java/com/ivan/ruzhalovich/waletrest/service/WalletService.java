package com.ivan.ruzhalovich.waletrest.service;

import com.ivan.ruzhalovich.waletrest.WalletRequest;
import com.ivan.ruzhalovich.waletrest.entity.Wallet;

import java.util.UUID;

public interface WalletService {
    public void newWallet(Wallet wallet);
    public Wallet executeTheOperation (WalletRequest request);
    public long getBalance(UUID wallet_id);
}
