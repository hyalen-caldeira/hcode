package us.hyalen.hcode.server.core.topic.v1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.server.core.BaseDao;

@Component("topicDao_v1")
@Transactional
public class TopicDao extends BaseDao {
}
