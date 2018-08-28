package us.hyalen.hcode.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ALGORITHM", schema = "HCODEDB")
@Getter
@Setter
@NoArgsConstructor
public class AlgorithmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
}
