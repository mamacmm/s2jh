package lab.s2jh.schedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lab.s2jh.core.entity.BaseEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 任务计划的数据库配置记录
 */
@Entity
@Table(name = "T_JOB_BEAN_CFG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JobBeanCfg extends BaseEntity<String> {

    private static final long serialVersionUID = 5278478312111449372L;

    /** 实现QuartzJobBean的类全路径类名 */
    private String jobClass;
    /** 定时表达式，基于CRON语法 */
    private String cronExpression;
    /** 自动初始运行 */
    private Boolean autoStartup;
    /** 启用运行历史记录 */
    private Boolean logRunHist;
    /** 描述 */
    private String description;

    private String id;

    @Id
    @Column(length = 40)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Column(length = 512, nullable = false, unique = true)
    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    @Column(length = 64, nullable = false)
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Column(length = 1000, nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(Boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

    @Override
    @Transient
    public String getDisplayLabel() {
        return jobClass + ":" + cronExpression;
    }
}
