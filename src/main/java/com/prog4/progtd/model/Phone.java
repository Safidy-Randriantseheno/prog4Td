package com.prog4.progtd.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    private String countryCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_employee", referencedColumnName = "id")
    private Employee phoneEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_enterprise", referencedColumnName = "id")
    private Enterprise phoneEnterprise;

}
