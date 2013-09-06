package lab.s2jh.schedule.service;

import lab.s2jh.core.dao.BaseDao;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.schedule.entity.JobBeanCfg;
import lab.s2jh.schedule.dao.JobBeanCfgDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobBeanCfgService extends BaseService<JobBeanCfg,String>{
    
    @Autowired
    private JobBeanCfgDao jobBeanCfgDao;

    @Override
    protected BaseDao<JobBeanCfg, String> getEntityDao() {
        return jobBeanCfgDao;
    }
}
