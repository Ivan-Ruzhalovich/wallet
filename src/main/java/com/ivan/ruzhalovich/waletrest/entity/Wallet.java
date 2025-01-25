package com.ivan.ruzhalovich.waletrest.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(name = "wallet_id")
    private UUID id;

    @Column(name = "balance")
    private long balance;

    @Version
    @Column(name = "serial")
    private long serial;

    public Wallet(UUID id, long balance, long serial) {
        this.id = id;
        this.balance = balance;
        this.serial = serial;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long amount) {
        this.balance = amount;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public Wallet() {
    }

    public Wallet(UUID id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", amount=" + balance +
                ", serial=" + serial +
                '}';
    }
}
