package com.example.services_wallet.Controller;

import com.example.services_wallet.AppExceptions.WalletBadRequest;
import com.example.services_wallet.AppExceptions.WalletNotFoundException;
import com.example.services_wallet.Model.Wallet;
import com.example.services_wallet.Repository.TransactionRepository;
import com.example.services_wallet.Repository.WalletRepository;
import com.example.services_wallet.Util.WalletValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletResource {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    WalletValidator walletValidator = new WalletValidator();
    private static final Logger logger = LoggerFactory.getLogger(WalletResource.class);

    @ApiOperation(value = "Find all the wallets")
    @GetMapping("/wallets")
    public List<Wallet> findAllWallet() {
        return walletRepository.findAll();
    }

    @ApiOperation(value = "Find a given wallet")
    @GetMapping("/wallets/{id}")
    public Wallet findAWallet(@ApiParam(value = "Stores id of the point of service to deliver to/collect from", required = true) @PathVariable int id) {
        logger.info("/wallets/{id} called with id: " + id);
        return walletRepository.findById(id).orElseThrow(() -> new WalletNotFoundException(id));
    }

    @ApiOperation(value = "Create new wallet!")
    @PostMapping("/wallets")
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet createWallet(@RequestBody Wallet wallet) {
        if(!walletValidator.validateRequest(wallet)) {
            logger.info("createWallet request not valid!!!");
            throw new WalletBadRequest();
        }
        Wallet wallet1 = walletRepository.save(wallet);
        return wallet1;
    }

    @ApiOperation(value = "Update an existing wallet")
    @PutMapping("/wallets/{id}")
    public Wallet updateWallet(@PathVariable int id, @RequestBody Wallet wallet) {
        if(walletRepository.findById(id) != null) {
            walletRepository.save(wallet);
            return wallet;
        } else {
            return null;
        }
    }
}
