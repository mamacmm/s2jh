package lab.s2jh.schedule.service;

import lab.s2jh.core.dao.BaseDao;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.schedule.entity.JobRunHist;
import lab.s2jh.schedule.dao.JobRunHistDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobRunHistService extends BaseService<JobRunHist,String>{
    
    @Autowired
    private JobRunHistDao jobRunHistDao;

    @Override
    protected BaseDao<JobRunHist, String> getEntityDao() {
        return jobRunHistDao;
    }
}
