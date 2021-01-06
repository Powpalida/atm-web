package th.ac.ku.atm.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    private List<BankAccount> bankAccountList;

    @PostConstruct
    public void postConstruct(){
        this.bankAccountList = new ArrayList<>();    //ส่งเป็น dependency
    }

    public void createBankAccount(BankAccount bankAccount){
        bankAccountList.add(bankAccount);
    }
    //find BankAccount ส่งid bnk เหมือน customer
    public  BankAccount findBankAccount(int id){
        for (BankAccount bankAccount : bankAccountList){
            if (bankAccount.getId() == id)
                return bankAccount;
        }
        return null;
    }
    //checkId
    public  BankAccount checkId(BankAccount inputBankAccount){
        //หา bankAccount ที่มี id ตรงก่อน
        BankAccount storedBankAccount = findBankAccount(inputBankAccount.getId());

        if (storedBankAccount != null){
            return  storedBankAccount;
        }
        return null;
    }

    //เพื่อแสดงผล
    public List<BankAccount> getBankAccountList() {
        return new ArrayList<>(this.bankAccountList);
    }

}
