package us.hyalen.hcode.core.topic.v1;

import lombok.Setter;
import us.hyalen.hcode.core.Domain;

public class Topic extends Domain {
    @Setter
    private static TopicDao topicDao;
}
