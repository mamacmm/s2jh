package lab.s2jh.schedule.web.action;

import java.util.List;
import java.util.Map;

import lab.s2jh.core.annotation.MetaData;
import lab.s2jh.core.context.SpringContextHolder;
import lab.s2jh.core.web.annotation.SecurityControllIgnore;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ModelDriven;

@MetaData(title = "任务管理")
public class SpringController extends RestActionSupport implements ModelDriven<Object> {

    protected final static Logger logger = LoggerFactory.getLogger(SpringController.class);

    private Object model;

    @Override
    public Object getModel() {
        return model;
    }

    @SecurityControllIgnore
    public HttpHeaders index() {
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    @MetaData(title = "任务列表")
    public HttpHeaders list() throws IllegalAccessException {
        ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor = (ScheduledAnnotationBeanPostProcessor) SpringContextHolder
                .getApplicationContext().getBean(AnnotationConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME);

        List<TaskVO> taskVOs = Lists.newArrayList();

        Map<Runnable, Long> fixedRateTasks = (Map<Runnable, Long>) FieldUtils.readDeclaredField(
                scheduledAnnotationBeanPostProcessor, "fixedRateTasks", true);
        logger.debug("fixedRateTasks:");
        for (Map.Entry<Runnable, Long> me : fixedRateTasks.entrySet()) {
            ScheduledMethodRunnable smr = (ScheduledMethodRunnable) me.getKey();
            TaskVO vo = new TaskVO();
            vo.setClazz(smr.getTarget().getClass().getName());
            vo.setMethod(smr.getMethod().getName());
            vo.setValue(String.valueOf(me.getValue()));
            vo.setType("fixedRate");
            taskVOs.add(vo);
            logger.debug(" - {}", vo);
        }

        Map<Runnable, Long> fixedDelayTasks = (Map<Runnable, Long>) FieldUtils.readDeclaredField(
                scheduledAnnotationBeanPostProcessor, "fixedDelayTasks", true);
        logger.debug("fixedDelayTasks:");
        for (Map.Entry<Runnable, Long> me : fixedDelayTasks.entrySet()) {
            ScheduledMethodRunnable smr = (ScheduledMethodRunnable) me.getKey();
            TaskVO vo = new TaskVO();
            vo.setClazz(smr.getTarget().getClass().getName());
            vo.setMethod(smr.getMethod().getName());
            vo.setValue(String.valueOf(me.getValue()));
            vo.setType("fixedDelay");
            taskVOs.add(vo);
            logger.debug(" - {}", vo);
        }

        Map<Runnable, Long> cronTasks = (Map<Runnable, Long>) FieldUtils.readDeclaredField(
                scheduledAnnotationBeanPostProcessor, "cronTasks", true);
        logger.debug("cronTasks:");
        for (Map.Entry<Runnable, Long> me : cronTasks.entrySet()) {
            ScheduledMethodRunnable smr = (ScheduledMethodRunnable) me.getKey();
            TaskVO vo = new TaskVO();
            vo.setClazz(smr.getTarget().getClass().getName());
            vo.setMethod(smr.getMethod().getName());
            vo.setValue(String.valueOf(me.getValue()));
            vo.setType("cron");
            taskVOs.add(vo);
            logger.debug(" - {}", vo);
        }

        model = new PageImpl<TaskVO>(taskVOs);
        return new DefaultHttpHeaders().disableCaching();
    }

    public class TaskVO {
        private String type;
        private String clazz;
        private String method;
        private String value;

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "TaskVO [clazz=" + clazz + ", method=" + method + ", value=" + value + ", type=" + type + "]";
        }

    }
}
