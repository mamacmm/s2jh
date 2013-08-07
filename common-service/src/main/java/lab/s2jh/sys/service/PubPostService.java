package lab.s2jh.sys.service;

import java.util.Date;
import java.util.List;

import lab.s2jh.core.dao.BaseDao;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.sys.dao.PubPostDao;
import lab.s2jh.sys.entity.PubPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PubPostService extends BaseService<PubPost, String> {

    @Autowired
    private PubPostDao pubPostDao;

    @Override
    protected BaseDao<PubPost, String> getEntityDao() {
        return pubPostDao;
    }

    public List<PubPost> findPublished() {
        return pubPostDao.findPublished(new Date());
    }
}
