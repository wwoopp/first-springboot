package com.wwoopp.firstspringboot;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Account {

    @GeneratedValue
    @Id
    Long id;

    @Column
    String username;

    @Column
    String password;


}
