package com.web.sample.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please enter Name")
    private String name;

    @NotBlank(message = "Please enter Email")
    @Email(message = "Please enter valid Email")
    private String email;

    @NotBlank(message = "Please enter Gender")
    @Pattern(regexp ="(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE)$", message = "Please enter valid Gender values")
    private String gender;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Address> address;
}
