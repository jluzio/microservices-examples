package com.example.microservices.playground.filtered;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/demo/userAccounts/mapping")
public class MappingFilteredUserAccountResource {
  @Autowired
  private UserAccountService service;

  @GetMapping
  @JsonView(UserAccount.Views.Simple.class)
  public MappingJacksonValue getAllAccounts() {
    var data = service.getAllAccounts();

    MappingJacksonValue mapping = new MappingJacksonValue(data);
    FilterProvider filters = new SimpleFilterProvider()
        .addFilter("filterSimple", SimpleBeanPropertyFilter.filterOutAllExcept("id"));
    mapping.setFilters(filters);
    return mapping;
  }

  @GetMapping("{id}")
  @JsonView(UserAccount.Views.Detailed.class)
  public MappingJacksonValue getAccount(@PathVariable("id") String id) {
    var data = service.getAllAccounts().stream()
        .filter(v -> Objects.equals(v.getId(), id))
        .findFirst()
        .get();
    MappingJacksonValue mapping = new MappingJacksonValue(data);
    FilterProvider filters = new SimpleFilterProvider()
        .addFilter("filterDetailed", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "email"));
    mapping.setFilters(filters);
    return mapping;
  }

}
