package lab.s2jh.sys.web.action;

import lab.s2jh.core.annotation.MetaData;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.core.web.BaseController;
import lab.s2jh.sys.entity.PubPost;
import lab.s2jh.sys.service.AttachmentFileService;
import lab.s2jh.sys.service.PubPostService;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("all")
@MetaData(value = "公告管理")
public class PubPostController extends BaseController<PubPost, String> {

    @Autowired
    private PubPostService pubPostService;

    @Autowired
    private AttachmentFileService attachmentFileService;

    @Override
    protected BaseService<PubPost, String> getEntityService() {
        return pubPostService;
    }

    @Override
    protected void checkEntityAclPermission(PubPost entity) {
        // TODO Add acl check code logic
    }

    @Override
    @MetaData(value = "创建")
    public HttpHeaders doCreate() {
        String fileId = this.getParameter("r2FileId");
        if (StringUtils.isNotBlank(fileId)) {
            bindingEntity.setR2File(attachmentFileService.findOne(fileId));
        }
        return super.doCreate();
    }

    @Override
    @MetaData(value = "更新")
    public HttpHeaders doUpdate() {
        String fileId = this.getParameter("r2FileId");
        if (StringUtils.isNotBlank(fileId)) {
            bindingEntity.setR2File(attachmentFileService.findOne(fileId));
        } else {
            bindingEntity.setR2File(null);
        }
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
