package com.prog4.progtd.modele;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"ref_incrementetion\"")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RefIncrementation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "incrementation")
    private int inccremeentaionEmployee;
}
