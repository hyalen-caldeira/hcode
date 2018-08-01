package us.hyalen.hcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.hyalen.hcode.model.AlgorithmModel;

@Repository
public interface AlgorithmRepository extends JpaRepository<AlgorithmModel, Long> {
}
