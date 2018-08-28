package us.hyalen.hcode.server.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.server.core.algorithm.v1.Algorithm;
import us.hyalen.hcode.server.core.algorithm.v1.AlgorithmDao;

import javax.annotation.PostConstruct;

@Component
public class AlgorithmDaoConfig {
    @Autowired
    @Qualifier("algorithmDao_v1")
    AlgorithmDao algorithmDao_v1;

    @PostConstruct
    void injectDependencies() {
        Algorithm.setAlgorithmDao(algorithmDao_v1);
    }
}
