package lab.s2jh.common.web.action;

import lab.s2jh.core.annotation.MetaData;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.core.web.BaseController;
import lab.s2jh.sys.entity.PubPost;
import lab.s2jh.sys.service.PubPostService;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

@Namespace("/pub")
@MetaData(title = "公告消息显示")
public class PubPostController extends BaseController<PubPost,String> {

    @Autowired
    private PubPostService pubPostService;

    @Override
    protected BaseService<PubPost, String> getEntityService() {
        return pubPostService;
    }
    
    @Override
    protected void checkEntityAclPermission(PubPost entity) {
        // Nothing to do
    }

    @MetaData(title = "公告列表")
    public HttpHeaders list() {
        setModel(pubPostService.findPublished());
        return buildDefaultHttpHeaders("list");
    }

    @Override
    @MetaData(title = "查询")
    public HttpHeaders findByPage() {
        return super.findByPage();
    }
}