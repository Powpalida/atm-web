package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {
    //handle request เกี่ยวกับบัญชีธนาคาร

    //มี dependency ไปที่ BankAccountService ต้องสร้าง constructor
    private BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public  String getBankAccountPage(Model model){
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model){
        accountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id,Model model){
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount",bankAccount);
        return "bankaccount-edit";
    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              double inputAmount, String transaction, Model model){
        BankAccount record = accountService.getBankAccount(bankAccount.getId()) ;
        if (transaction.equals("deposit")) {
            record.setBalance(record.getBalance() + inputAmount);
        } else {
            record.setBalance(record.getBalance() - inputAmount);
        }
        accountService.editBankAccount(record);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    //รับข้อมูลบัญชี
    @GetMapping("/delete/{id}")
    public String getDeleteBankAccount(@PathVariable int id,Model model){
        BankAccount bankAccount = accountService.getBankAccount(id);
        accountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankAccount",bankAccount);
        return "bankaccount";
    }
    //
    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,@ModelAttribute BankAccount bankAccount,Model model){
        accountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }
}
