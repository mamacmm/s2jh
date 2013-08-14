package lab.s2jh.schedule;

import java.io.Serializable;
import java.text.ParseException;

import lab.s2jh.schedule.entity.TriggerCfg;
import lab.s2jh.schedule.service.TriggerCfgService;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * 扩展标准Trigger，实现从数据库加载CronExpression表达式配置
 */
public class InitializingCronTrigger extends CronTriggerBean implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(InitializingCronTrigger.class);

    private TriggerCfgService triggerCfgService;

    public void setTriggerCfgService(TriggerCfgService triggerCfgService) {
        this.triggerCfgService = triggerCfgService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();

        TriggerCfg triggerCfg = triggerCfgService.findByGroupAndName(Scheduler.DEFAULT_GROUP, this.getName());
        try {
            if (triggerCfg != null && StringUtils.isNotBlank(triggerCfg.getCronExpression())) {
                logger.info("Override trigger [{}] CronExpression for XML value [{}] with Database value [{}]",
                        Scheduler.DEFAULT_GROUP + "." + this.getName(), this.getCronExpression(),
                        triggerCfg.getCronExpression());
                this.setCronExpression(triggerCfg.getCronExpression());
            }
        } catch (ParseException e) {
            logger.error("Quartz trigger CronExpression error", e);
        }
    }
}
