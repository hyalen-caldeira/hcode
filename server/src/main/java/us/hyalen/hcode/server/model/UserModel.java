package us.hyalen.hcode.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import us.hyalen.hcode.server.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USERNAME"}),
        @UniqueConstraint(columnNames = {"EMAIL"})},
    schema = "HCODEDB")
@Setter
@Getter
@NoArgsConstructor
public class UserModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Size(max = 40)
    @Column(name = "USERNAME")
    private String username;

    @NotBlank
    @Size(max = 100)
    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleModel> roles = new HashSet<>();
}