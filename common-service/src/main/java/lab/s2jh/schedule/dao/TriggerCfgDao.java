package lab.s2jh.schedule.dao;

import java.util.List;

import javax.persistence.QueryHint;

import lab.s2jh.core.dao.BaseDao;
import lab.s2jh.schedule.entity.TriggerCfg;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface TriggerCfgDao extends BaseDao<TriggerCfg, String> {

    @Query("from TriggerCfg")
    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    List<TriggerCfg> findAllCached();
}