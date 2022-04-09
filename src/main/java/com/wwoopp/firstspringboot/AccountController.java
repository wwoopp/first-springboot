package com.wwoopp.firstspringboot;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@Controller
@AllArgsConstructor
public class AccountController {

    AccountRepository accountRepository;

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

    @PostMapping("/account/login")
    String performLogin(String username, String password, Model model) {
        model.addAttribute("isSuccess", false);

        this.accountRepository.findByUsername(username).ifPresent(account -> {

            if (password.equals(account.getPassword())) {
                model.addAttribute("isSuccess", true);
            }
        });

        return "account/performLogin";
    }
}
