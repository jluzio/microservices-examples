package com.example.microservices.playground.filtered;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/demo/userAccounts/jsonView")
public class JsonViewFilteredUserAccountResource {
  @Autowired
  private UserAccountService service;

  @GetMapping
  @JsonView(UserAccount.Views.Simple.class)
  public List<UserAccount> getAllAccounts() {
    return service.getAllAccounts();
  }

  @GetMapping("{id}")
  @JsonView(UserAccount.Views.Detailed.class)
  public UserAccount getAccount(@PathVariable("id") String id) {
    return service.getAllAccounts().stream()
        .filter(v -> Objects.equals(v.getId(), id))
        .findFirst()
        .get();
  }

}
