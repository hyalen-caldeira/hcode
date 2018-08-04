package us.hyalen.hcode.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 40)
    private RoleName name;
}