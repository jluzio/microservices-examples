package com.example.microservices.playground.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/demo/users")
public class DemoUserResource {
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public Iterable<User> listUsers() {
    return userRepository.findAll();
  }

  @GetMapping("{id}")
  public EntityModel<User> getUser(@PathVariable("id") Integer id) {
    var user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(String.format("id: %s", id)));
    return EntityModel.of(user,
        linkTo(methodOn(getClass()).listUsers()).withRel("users"),
        linkTo(methodOn(getClass()).listUsers()).withRel("users"));
  }

  @PostMapping
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    var savedUser = userRepository.save(user);
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();
    return ResponseEntity
        .created(location)
        .build();
  }

  @PutMapping("{id}")
  public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
    return userRepository.save(user);
  }

  @DeleteMapping("{id}")
  public void deleteUser(@PathVariable("id") Integer id) {
    try {
      userRepository.deleteById(id);
    } catch (Exception e) {
      throw new UserNotFoundException(String.format("id=%s", id));
    }
  }

  @GetMapping("search")
  public Page<User> searchUsers(UserFilter userFilter) {
    var example = Example.of(userFilter.getUser());
    var page = userFilter.getPage() != null ? userFilter.getPage().toPageable() : Pageable.unpaged();
    return userRepository.findAll(example, page);
  }

}
