package lab.s2jh.core.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("all")
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface HibEventListeners {
    /** The callback listener classes */
    Class[] value();
}
