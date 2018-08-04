package us.hyalen.hcode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "COMPANY", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "NAME"
        })
})
@Getter
@Setter
@NoArgsConstructor
public class CompanyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 40)
    @Column (name = "NAME")
    private String name;
}
