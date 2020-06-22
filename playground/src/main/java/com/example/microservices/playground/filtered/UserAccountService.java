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
        .map(id -> {
              var user = new UserAccount();
              user.setId(id);
              user.setName(String.format("name-%s", id));
              user.setEmail(String.format("name-%s@mail.org", id));
              user.setPassword(String.format("pass-%s", id));
              return user;
            }
        )
        .collect(Collectors.toList());
  }
}
