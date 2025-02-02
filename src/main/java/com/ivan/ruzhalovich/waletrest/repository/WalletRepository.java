package com.ivan.ruzhalovich.waletrest.repository;

import com.ivan.ruzhalovich.waletrest.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID>{};
