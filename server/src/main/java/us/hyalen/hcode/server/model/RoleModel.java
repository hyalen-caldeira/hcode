package us.hyalen.hcode.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import us.hyalen.hcode.server.model.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name = "ROLE", schema = "HCODEDB")
@Getter
@Setter
@NoArgsConstructor
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "NAME", length = 40)
    private RoleName name;
}