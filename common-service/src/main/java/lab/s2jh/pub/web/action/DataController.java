package lab.s2jh.pub.web.action;

import lab.s2jh.core.context.KernelConfigParameters;

import org.apache.struts2.rest.RestActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公共数据服务处理
 */
@SuppressWarnings("all")
public class DataController extends RestActionSupport {

    @Autowired
    private KernelConfigParameters kernelConfigParameters;


}
