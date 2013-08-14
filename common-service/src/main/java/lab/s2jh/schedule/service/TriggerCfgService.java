package lab.s2jh.schedule.service;

import java.util.List;

import lab.s2jh.core.dao.BaseDao;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.schedule.dao.TriggerCfgDao;
import lab.s2jh.schedule.entity.TriggerCfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TriggerCfgService extends BaseService<TriggerCfg, String> {

    @Autowired
    private TriggerCfgDao triggerCfgDao;

    @Override
    protected BaseDao<TriggerCfg, String> getEntityDao() {
        return triggerCfgDao;
    }

    public TriggerCfg findByGroupAndName(String triggerGroup, String triggerName) {
        List<TriggerCfg> triggerCfgs = triggerCfgDao.findAllCached();
        for (TriggerCfg triggerCfg : triggerCfgs) {
            if (triggerGroup.equals(triggerCfg.getTriggerGroup()) && triggerName.equals(triggerCfg.getTriggerName())) {
                return triggerCfg;
            }
        }
        return null;
    }
}
