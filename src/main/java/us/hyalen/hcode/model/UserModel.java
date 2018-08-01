package us.hyalen.hcode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.hyalen.hcode.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "USER_LOGIN"
        }),
        @UniqueConstraint(columnNames = {
                "USER_EMAIL"
        })
})
@Getter
@Setter
@NoArgsConstructor
public class UserModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @NotBlank
    @Size(max = 40)
    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @NotBlank
    @Size(max = 15)
    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(name = "USER_EMAIL")
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(name = "USER_LOGIN")
    private String login;

    @NotBlank
    @Size(max = 100)
    @Column(name = "USER_PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleModel> roles = new HashSet<>();
}