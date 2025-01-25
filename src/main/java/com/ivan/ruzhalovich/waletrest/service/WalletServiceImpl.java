package com.ivan.ruzhalovich.waletrest.service;

import com.ivan.ruzhalovich.waletrest.WalletRequest;
import com.ivan.ruzhalovich.waletrest.entity.Wallet;
import com.ivan.ruzhalovich.waletrest.exceptions.IncorrectOperationType;
import com.ivan.ruzhalovich.waletrest.exceptions.IncorrectUUIDException;
import com.ivan.ruzhalovich.waletrest.exceptions.NoMoneyException;
import com.ivan.ruzhalovich.waletrest.repository.WalletRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    private static final int MAX_REPEAT_COUNTER = 10;

    @Autowired
    WalletRepository repository;

    @Transactional
    @Override
    public void newWallet(Wallet wallet) {
        repository.save(wallet);
    }

    @Transactional
    @Override
    public Wallet executeTheOperation(WalletRequest request) {
        Optional<Wallet> walletOptional = repository.findById(request.getValletId());
        if (walletOptional.isEmpty()) {
            throw new IncorrectUUIDException("Кошелек с данным UUID отсутствует в базе данных!");
        }
        Wallet currentWallet = walletOptional.get();
        if (!trySaveCycle(currentWallet,request))
            throw new ConcurrencyFailureException("Не удалось выполнить операцию " + request.getOperationType()
                    + ". Пожалуйста повторите позже.");
        return currentWallet;
    }

    @Override
    public long getBalance(UUID wallet_id) {
        Optional<Wallet> optionalUUID = repository.findById(wallet_id);
        if (optionalUUID.isPresent()) {
            return optionalUUID.get().getBalance();
        } else throw new IncorrectUUIDException("Кошелек с данным UUID отсутствует в базе данных!");
    }

    public void checkOperationTypeAndMoneySum(Wallet wallet, WalletRequest request) {
        long currentBalance = wallet.getBalance();
        switch (request.getOperationType()) {
            case ("DEPOSIT"):
                wallet.setBalance(currentBalance + request.getAmount());
                break;
            case ("WITHDRAW"):
                if (currentBalance >= request.getAmount()) {
                    wallet.setBalance(currentBalance - request.getAmount());
                    break;
                } else {
                    throw new NoMoneyException("Недостаточно денег на счете!");
                }
            default:
                throw new IncorrectOperationType("Не корректный тип операции");

        }
    }

    public boolean trySaveCycle(Wallet wallet,WalletRequest request){
        boolean isExecution = false;
        int counter = 0;
        while (!isExecution && counter < MAX_REPEAT_COUNTER) {
            counter++;
            isExecution = true;
            checkOperationTypeAndMoneySum(wallet,request);
            try {
                repository.save(wallet);

            } catch (OptimisticLockException ignored) {
                isExecution = false;
                System.out.println("Поймали OptimisticLockException, counter = "
                        + counter + ". Осталось попыток : " + (MAX_REPEAT_COUNTER - counter));
            }
        }
        return isExecution;
    }

}
