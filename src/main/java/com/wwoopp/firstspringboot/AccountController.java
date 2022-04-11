package com.wwoopp.firstspringboot;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

@Controller
@AllArgsConstructor
public class AccountController {

    AccountRepository accountRepository;
    AccountSessionHolder accountSessionHolder;

    @PostConstruct
    void load() {
        this.accountRepository.save(Account.builder()
                .username("happy")
                .password("1234")
                .build());
    }

    @GetMapping("/account/login")
    String login() {
        return "account/login";
    }

    @GetMapping("/account/status")
    String status(Model model) {
        model.addAttribute("loginStatus", false);

        if (accountSessionHolder.isLogon()) {
            model.addAttribute("loginStatus", true);
            model.addAttribute("loginAccountId", accountSessionHolder.getAccountId());
        }

        return "account/status";
    }

    @PostMapping("/account/login")
    String performLogin(String username, String password, Model model) {
        model.addAttribute("loginStatus", false);

        this.accountRepository.findByUsername(username).ifPresent(account -> {

            if (password.equals(account.getPassword())) {
                accountSessionHolder.setAccountId(account.getId());
                model.addAttribute("loginStatus", true);
            }
        });

        return "account/status";
    }
}
