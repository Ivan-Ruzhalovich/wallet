package com.ivan.ruzhalovich.waletrest.service;


import com.ivan.ruzhalovich.waletrest.WalletRequest;
import com.ivan.ruzhalovich.waletrest.entity.Wallet;
import com.ivan.ruzhalovich.waletrest.exceptions.IncorrectOperationType;
import com.ivan.ruzhalovich.waletrest.exceptions.IncorrectUUIDException;
import com.ivan.ruzhalovich.waletrest.exceptions.NoMoneyException;
import com.ivan.ruzhalovich.waletrest.repository.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WalletServiceImplsTest {

    private final UUID id = UUID.randomUUID();
    private final Wallet wallet = new Wallet(id, 10000);
    private final WalletRequest correctWithdrawRequest = new WalletRequest(id, "WITHDRAW", 20000);
    private final WalletRequest correctDepositRequest = new WalletRequest(id, "DEPOSIT", 20000);
    private final WalletRequest inCorrectRequest = new WalletRequest(id, "QWERTY", 20000);


    @InjectMocks
    private WalletServiceImpl walletService;

    @Mock
    private WalletRepository repository;


    @Test
    void executeTheOperationTest() {
        findByIdTest();
        getBalanceWithIncorrectUUIDExceptionTest();
        incorrectOperationTypeExceptionTest();
        noMoneyExceptionTest();
    }

    @Test
    void getBalanceTest() {
        findByIdTest();
        getBalanceWithIncorrectUUIDExceptionTest();
    }

    @Test
    void trySaveCycleTest(){
        Mockito.when(walletService.trySaveCycle(wallet,correctDepositRequest)).thenReturn(true);
    }

    @Test
    void incorrectOperationTypeExceptionTest() {
        Assertions.assertThrows(IncorrectOperationType.class, () -> walletService.checkOperationTypeAndMoneySum(wallet, inCorrectRequest));
    }

    @Test
    void noMoneyExceptionTest() {
        Assertions.assertThrows(NoMoneyException.class, () -> walletService.checkOperationTypeAndMoneySum(wallet, correctWithdrawRequest));
    }

    @Test
    void findByIdTest() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(wallet));
        Optional<Wallet> optionalWallet = repository.findById(id);
        Assertions.assertEquals(10000, optionalWallet.get().getBalance());
    }

    @Test
    void getBalanceWithIncorrectUUIDExceptionTest() {
        Assertions.assertThrows(IncorrectUUIDException.class, () -> walletService.getBalance(UUID.randomUUID()));
        Assertions.assertThrows(IncorrectUUIDException.class, () -> walletService.getBalance(null));
    }
}
