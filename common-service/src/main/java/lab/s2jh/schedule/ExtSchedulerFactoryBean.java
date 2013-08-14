package lab.s2jh.schedule;

import lab.s2jh.schedule.entity.TriggerCfg;
import lab.s2jh.schedule.service.TriggerCfgService;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 扩展标准的SchedulerFactoryBean，实现基于数据库配置的任务管理器初始化
 */
public class ExtSchedulerFactoryBean extends SchedulerFactoryBean {

    private static Logger logger = LoggerFactory.getLogger(ExtSchedulerFactoryBean.class);

    private TriggerCfgService triggerCfgService;

    public void setTriggerCfgService(TriggerCfgService triggerCfgService) {
        this.triggerCfgService = triggerCfgService;
    }

    public void initialization() throws SchedulerException {
        Scheduler scheduler = getScheduler();
        String[] triggerGroupNames = scheduler.getTriggerGroupNames();
        for (String triggerGroupName : triggerGroupNames) {
            String[] triggerNames = scheduler.getTriggerNames(triggerGroupName);
            for (String triggerName : triggerNames) {
                TriggerCfg triggerCfg = triggerCfgService.findByGroupAndName(triggerGroupName, triggerName);
                if (triggerCfg != null) {
                    if (triggerCfg.getAutoStartup()) {
                        logger.debug("Run trigger according to ScheduleTriggerCfg: {}.{}: autoStartup=true",
                                triggerGroupName, triggerName);
                        scheduler.resumeTrigger(triggerName, triggerGroupName);
                    } else {
                        logger.debug("Pause trigger according to ScheduleTriggerCfg: {}.{}: autoStartup=false",
                                triggerGroupName, triggerName);
                        scheduler.pauseTrigger(triggerName, triggerGroupName);
                    }
                } else {
                    logger.warn("Default Run trigger as NO TriggerCfg Found: {}.{}", triggerGroupName, triggerName);
                    scheduler.resumeTrigger(triggerName, triggerGroupName);
                }
            }
        }
    }

    public Scheduler getScheduler() {
        return (Scheduler) this.getObject();
    }

}
