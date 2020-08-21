package com.example.services_wallet.Controller;

import com.example.services_wallet.AppExceptions.TransactionBadRequest;
import com.example.services_wallet.Model.AddBalanceDetails;
import com.example.services_wallet.Model.Transaction;
import com.example.services_wallet.Model.User;
import com.example.services_wallet.Model.Wallet;
import com.example.services_wallet.Repository.TransactionRepository;
import com.example.services_wallet.Repository.WalletRepository;
import com.example.services_wallet.Service.EmailService;
import com.example.services_wallet.Service.UserService;
import com.example.services_wallet.Util.TransactionValidator;
import com.example.services_wallet.Util.WalletValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
public class TransactionResource {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserService userService;

    WalletValidator walletValidator = new WalletValidator();
    TransactionValidator transactionValidator = new TransactionValidator();
    private static final String topic = "test";
    private static final Logger logger = LoggerFactory.getLogger(WalletResource.class);

    @PostMapping("/sendMoney")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addBalance(@RequestBody Transaction transaction) throws Exception {
        if(!transactionValidator.validateRequest(transaction)){
            throw new TransactionBadRequest();
        }
        transaction.setTrDateAndTime(new Date(Calendar.getInstance().getTime().getTime()));
        User sender = userService.getAUser(transaction.getSendersId());
        User receiver = userService.getAUser(transaction.getReceiversId());
        if(sender == null || receiver == null) {
            logger.info("No wallet for sender or receiver");
            throw new TransactionBadRequest();
        }
        Wallet senderWallet = walletRepository.findWalletByUserId(sender.getUserId());
        Wallet receiverWallet = walletRepository.findWalletByUserId(receiver.getUserId());
        int amt = transaction.getTrAmt();
        if(senderWallet.getWalBal() < amt) {
            throw new Exception("Insufficient balance!");
        }
        senderWallet.setWalBal(senderWallet.getWalBal() - amt);
        receiverWallet.setWalBal(receiverWallet.getWalBal() + amt);
        transaction.setStatus("SUCCESS");
        walletRepository.save(receiverWallet);
        walletRepository.save(senderWallet);
        return transactionRepository.save(transaction);
    }

    @GetMapping("/getBal/{id}")
    public int getBalance(@PathVariable int id) throws Exception {
        Wallet wallet = walletRepository.findWalletByUserId(id);
        if(wallet == null) throw new Exception("Wallet not found");
        else
            return wallet.getWalBal();
    }

    @PostMapping("/addBalance")
    public AddBalanceDetails addBalance(@RequestBody AddBalanceDetails request) {
        logger.info(request.toString());
        Wallet wallet = walletRepository.findWalletByUserId(request.getuId());
        wallet.setWalBal(request.getAmount() + wallet.getWalBal());
        walletRepository.save(wallet);
        return request;
    }

    @GetMapping("/txnHistory/{id}")
    public String getTransactionHistory(@PathVariable int id) {
        logger.info(String.format("$$ -> Producing Transaction --> %s", id));
        sendTransactionHistory(String.valueOf(id));
        return "You will get the details on Mail";
    }

    private void sendTransactionHistory(String id) {
        int id1 = Integer.parseInt(id);
        ArrayList<Transaction> list = (ArrayList<Transaction>) transactionRepository.findBySidAndRid(id1);
        User user1 = userService.getAUser(id1);
        String fileName = "transaction.csv";
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < list.size(); i++) {
                fw.append(list.get(i).getStatus());
                fw.append(',');
                int amt = list.get(i).getTrAmt();
                Integer obj = amt;
                fw.append(obj.toString());
                fw.append(',');
                int id2 = list.get(i).getTrId();
                Integer obj2 = id2;
                fw.append(obj2.toString());
                fw.append(',');
                int rid = list.get(i).getReceiversId();
                obj = rid;
                fw.append(obj.toString());
                fw.append(',');
                int sid = list.get(i).getSendersId();
                obj = sid;
                fw.append(obj.toString());
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            logger.info("CSV file is created successfully!!");
            EmailService.sendEmailWithAttachments("","",user1.getUserEmail(),"",user1.getUserEmail(),"","",fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
