package us.hyalen.hcode.server.core.topic.v1;

import lombok.Setter;
import us.hyalen.hcode.server.core.Domain;

public class Topic extends Domain {
    @Setter
    private static TopicDao topicDao;
}
