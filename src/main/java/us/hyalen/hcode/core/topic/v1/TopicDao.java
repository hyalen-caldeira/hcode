package us.hyalen.hcode.core.topic.v1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.dao.BaseDao;

@Component("topicDao_v1")
@Transactional
public class TopicDao extends BaseDao {
}
