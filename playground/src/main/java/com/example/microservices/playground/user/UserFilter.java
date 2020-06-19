package com.example.microservices.playground.user;

import com.example.microservices.playground.query.PageRequestData;
import lombok.Data;

@Data
public class UserFilter {
  private User user;
  private PageRequestData page;
}
