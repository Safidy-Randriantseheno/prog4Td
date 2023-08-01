package com.prog4.progtd.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String slogan;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nif;

    @Column(nullable = false)
    private String stat;

    @Column(nullable = false)
    private String rcs;

    @Lob
    private byte[] logo;

    @OneToMany(mappedBy = "phoneEnterprise")
    private List<Phone> phones;
}
