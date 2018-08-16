package us.hyalen.hcode.server.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.server.core.topic.v1.Topic;
import us.hyalen.hcode.server.core.topic.v1.TopicDao;

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
