package lab.s2jh.task.service.test;

import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ActiveProfiles("test")
@ContextConfiguration(locations = { "classpath:/context/spring*.xml" })
public class ScheduledAnnotationBeanPostProcessorTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void findTasks() throws Exception {
        ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor = (ScheduledAnnotationBeanPostProcessor) this.applicationContext
                .getBean(AnnotationConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME);
        Assert.assertNotNull(scheduledAnnotationBeanPostProcessor);
        Map<Runnable, Long> fixedRateTasks = (Map<Runnable, Long>) FieldUtils.readDeclaredField(
                scheduledAnnotationBeanPostProcessor, "fixedRateTasks", true);
        Assert.assertNotNull(fixedRateTasks.size() >= 1);

        for (Map.Entry<Runnable, Long> me : fixedRateTasks.entrySet()) {
            ScheduledMethodRunnable smr = (ScheduledMethodRunnable) me.getKey();
            logger.debug(" - " + smr.getTarget() + "." + smr.getMethod() + ":" + me.getValue());
        }
    }
}
