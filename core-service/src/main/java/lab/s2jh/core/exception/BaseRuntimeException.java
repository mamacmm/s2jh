package lab.s2jh.core.exception;

import org.springframework.core.NestedRuntimeException;

@SuppressWarnings("all")
public abstract class BaseRuntimeException extends NestedRuntimeException{

    public BaseRuntimeException(String msg) {
        super(msg);
    }
    
    public BaseRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
