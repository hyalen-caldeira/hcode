package us.hyalen.hcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.hyalen.hcode.model.CompanyModel;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {
}
