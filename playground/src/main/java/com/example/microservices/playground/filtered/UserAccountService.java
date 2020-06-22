package com.example.microservices.playground.filtered;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UserAccountService {
  public List<UserAccount> getAllAccounts() {
    return IntStream.rangeClosed(1, 5)
        .mapToObj(Integer::toString)
        .map(id -> UserAccount.builder()
            .id(id)
            .name(String.format("name-%s", id))
            .email(String.format("name-%s@mail.org", id))
            .password(String.format("pass-%s", id))
            .build()
        )
        .collect(Collectors.toList());
  }
}
