package com.wwoopp.firstspringboot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class AccountSessionHolder {

    Long accountId;

    public boolean isLogon() {
        return accountId != null;
    }

    public void clearLoginSession() {
        this.accountId = null;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
