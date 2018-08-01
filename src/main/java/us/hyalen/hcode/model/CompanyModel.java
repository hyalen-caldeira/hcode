package us.hyalen.hcode.model;

import lombok.Data;
import us.hyalen.hcode.core.company.v1.Company;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COMPANY", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "companyName"
        })
})
@Data
public class CompanyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String companyName;

    public CompanyModel() {}

    public CompanyModel(Company company) {

    }
}
