package lab.s2jh.schedule;

import lab.s2jh.schedule.service.JobBeanCfgService;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 扩展标准的SchedulerFactoryBean，实现基于数据库配置的任务管理器初始化
 */
public class ExtSchedulerFactoryBean extends SchedulerFactoryBean {

    private static Logger logger = LoggerFactory.getLogger(ExtSchedulerFactoryBean.class);

    private JobBeanCfgService jobBeanCfgService;

    public void setJobBeanCfgService(JobBeanCfgService jobBeanCfgService) {
        this.jobBeanCfgService = jobBeanCfgService;
    }

    protected void registerJobsAndTriggers() throws SchedulerException {
        logger.debug("Invoking registerJobsAndTriggers...");
    }

}
