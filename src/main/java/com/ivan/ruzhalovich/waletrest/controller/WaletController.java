package com.ivan.ruzhalovich.waletrest.controller;

import com.ivan.ruzhalovich.waletrest.entity.Wallet;
import com.ivan.ruzhalovich.waletrest.WalletRequest;
import com.ivan.ruzhalovich.waletrest.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("api/v1")
public class WaletController {
    @Autowired
    private WalletService service;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> depositOrWithdraw(@RequestBody WalletRequest walletRequest) {
        Wallet updatedWallet = service.executeTheOperation(walletRequest);
        return ResponseEntity.ok(updatedWallet);
    }

    @GetMapping("/wallet/new")
    public ResponseEntity<Wallet> createNewWallet() {
        Wallet newWallet = new Wallet(UUID.randomUUID(), 0);
        try {
            service.newWallet(newWallet);
            return ResponseEntity.ok(newWallet);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка сохранения нового кошелька!");
        }
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public ResponseEntity<Wallet> getWalletBalance(@PathVariable UUID WALLET_UUID) {
        long balance = service.getBalance(WALLET_UUID);
        return ResponseEntity.ok(new Wallet(WALLET_UUID, balance));
    }

}
