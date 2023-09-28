package com.cpan252.assignmentOne.cpan252assignmentOne.model;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.RoleName;
import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleName name;


}