package th.ac.ku.atm.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BankAccountService {

    //private List<BankAccount> bankAccountList;
    private RestTemplate restTemplate;

    /*@PostConstruct
    public void postConstruct(){
        this.bankAccountList = new ArrayList<>();    //ส่งเป็น dependency
    }*/

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //ตาม id ที่ระบุ
    public List<BankAccount> getCustomerBankAccounts(int customerId){
        //connect to BankAccount API service
        String url = "http://localhost:8091/api/bankaccount/customer/" +customerId;

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url,BankAccount[].class); //การ get ที่ไปต่อกับ API
        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);     //คือค่าแปลงให้เป็น list

    }

    //ของ BankAccount ทั้งหมด
    public List<BankAccount> getBankAccounts(){
        String url = "http://localhost:8091/api/bankaccount/";

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url,BankAccount[].class); //การ get ที่ไปต่อกับ API
        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);
    }

    public void openAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount";

        restTemplate.postForObject(url,bankAccount,BankAccount.class);
    }

    public BankAccount getBankAccount(int id){
        String url = "http://localhost:8091/api/bankaccount/" + id;
        ResponseEntity<BankAccount> response =
                restTemplate.getForEntity(url,BankAccount.class);
        return response.getBody();

    }

    public void editBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/" + bankAccount.getId();
        restTemplate.put(url,bankAccount);
    }

   /* public void createBankAccount(BankAccount bankAccount){
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
    }*/
/*
    //เพื่อแสดงผล
    public List<BankAccount> getBankAccountList() {
        return new ArrayList<>(this.bankAccountList);
    }*/

}
