package lab.s2jh.biz.demo.web.action;

import lab.s2jh.core.annotation.MetaData;
import lab.s2jh.biz.demo.entity.Demo;
import lab.s2jh.biz.demo.service.DemoService;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.core.web.BaseController;
import lab.s2jh.core.web.view.OperationResult;

import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("all")
@MetaData(value = "[TODO控制器名称]")
public class DemoController extends BaseController<Demo,String> {

    @Autowired
    private DemoService demoService;

    @Override
    protected BaseService<Demo, String> getEntityService() {
        return demoService;
    }
    
    @Override
    protected void checkEntityAclPermission(Demo entity) {
        // TODO Add acl check code logic
    }

    @MetaData(value = "[TODO方法作用]")
    public HttpHeaders todo() {
        //TODO
        setModel(OperationResult.buildSuccessResult("TODO操作完成"));
        return buildDefaultHttpHeaders();
    }
    
    @Override
    @MetaData(value = "创建")
    public HttpHeaders doCreate() {
        return super.doCreate();
    }

    @Override
    @MetaData(value = "更新")
    public HttpHeaders doUpdate() {
        return super.doUpdate();
    }

    @Override
    @MetaData(value = "删除")
    public HttpHeaders doDelete() {
        return super.doDelete();
    }

    @Override
    @MetaData(value = "查询")
    public HttpHeaders findByPage() {
        return super.findByPage();
    }
}