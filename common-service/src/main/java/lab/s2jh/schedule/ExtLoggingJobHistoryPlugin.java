package lab.s2jh.schedule;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

import lab.s2jh.schedule.entity.JobRunHist;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.plugins.history.LoggingJobHistoryPlugin;

/**
 * 扩展实现LoggingJobHistoryPlugin约定接口
 * 转换Quartz提供的相关接口数据为ScheduleJobRunHist对象并调用对应的Service接口把数据写入数据库表中
 */
public class ExtLoggingJobHistoryPlugin extends LoggingJobHistoryPlugin {

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);
        //ScheduleJobRunHistService scheduleJobRunHistService = (ScheduleJobRunHistService) SpringContextHolder.getBean(ScheduleJobRunHistService.class);
        Trigger trigger = context.getTrigger();
        JobRunHist jobRunHist=new JobRunHist();
        jobRunHist.setTriggerGroup(trigger.getGroup());
        jobRunHist.setTriggerName(trigger.getName());
        jobRunHist.setJobName(context.getJobDetail().getName());
        jobRunHist.setJobGroup(context.getJobDetail().getGroup());
        jobRunHist.setFireTime(new java.util.Date());
        jobRunHist.setPreviousFireTime(trigger.getPreviousFireTime());
        jobRunHist.setNextFireTime(trigger.getNextFireTime());
        jobRunHist.setRefireCount(new Integer(context.getRefireCount()));
        if (jobException != null) {
            jobRunHist.setExceptionFlag(Boolean.TRUE);
            jobRunHist.setResult(jobException.getMessage());
            StringWriter strWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(new BufferedWriter(strWriter));
            jobException.printStackTrace(writer);
            writer.flush();
            strWriter.flush();
            String exceptionStack = strWriter.getBuffer().toString();
            jobRunHist.setExceptionStack(exceptionStack);
        } else {
            jobRunHist.setExceptionFlag(Boolean.FALSE);
            if(context.getResult()==null){
                jobRunHist.setResult("SUCCESS");
            }else{
                String result = String.valueOf(context.getResult());
                jobRunHist.setResult(result);
            }
        }
        //scheduleJobRunHistService.insertHistLog(jobRunHist);
    }

}