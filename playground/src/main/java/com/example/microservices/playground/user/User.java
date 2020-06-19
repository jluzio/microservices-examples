package com.example.microservices.playground.user;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@XmlRootElement
@Entity
@NamedQueries({
  @NamedQuery(name = "User.namedQueryFindByEmail", query = "select u from User u where u.email = ?1")
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank
	@Size(min = 3)
	@Schema(description = "Name required and at least 3 chars")
	private String name;
  @NotBlank
  @Size(min = 3)
  private String username;
  @NotBlank
  @Email
  private String email;
  @Embedded
  private Address address;

}
