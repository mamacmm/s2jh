package lab.s2jh.schedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lab.s2jh.core.annotation.MetaData;
import lab.s2jh.core.entity.BaseEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.quartz.Scheduler;

@Entity
@Table(name = "T_SCHEDULE_TRIGGER_CFG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@MetaData(title = "任务计划的数据库配置")
public class TriggerCfg extends BaseEntity<String> {

    /** 名称，对应于在Spring配置文件中定义的Bean的ID值 */
    private String triggerName;
    /** 分组，一般默认不做处理 */
    private String triggerGroup = Scheduler.DEFAULT_GROUP;
    /** 描述 */
    private String description;
    /** 定时表达式，基于CRON语法 */
    private String cronExpression;
    /** 初始运行状态 */
    private Boolean autoStartup = Boolean.TRUE;

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

    @Column(length = 64, nullable = false)
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Column(length = 64, nullable = true)
    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    @Column(length = 64, nullable = true)
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
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
        return triggerGroup + "/" + triggerName;
    }
}
