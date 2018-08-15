package us.hyalen.hcode.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.core.topic.v1.Topic;
import us.hyalen.hcode.core.topic.v1.TopicDao;

import javax.annotation.PostConstruct;

@Component
public class TopicDaoConfig {
    @Autowired
    @Qualifier("topicDao_v1")
    private TopicDao topicDao_v1;

    @PostConstruct
    void injectDependencies() {
        Topic.setTopicDao(topicDao_v1);
    }
}
