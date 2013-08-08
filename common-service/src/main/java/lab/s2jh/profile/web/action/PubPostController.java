package lab.s2jh.profile.web.action;

import java.util.Date;
import java.util.List;

import lab.s2jh.auth.entity.User;
import lab.s2jh.auth.service.UserService;
import lab.s2jh.core.annotation.MetaData;
import lab.s2jh.core.security.AuthContextHolder;
import lab.s2jh.core.service.BaseService;
import lab.s2jh.core.web.BaseController;
import lab.s2jh.sys.entity.PubPost;
import lab.s2jh.sys.entity.PubPostRead;
import lab.s2jh.sys.service.PubPostReadService;
import lab.s2jh.sys.service.PubPostService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

@MetaData(title = "公告消息显示")
public class PubPostController extends BaseController<PubPost, String> {

    @Autowired
    private PubPostService pubPostService;

    @Autowired
    private PubPostReadService pubPostReadService;

    @Autowired
    private UserService userService;

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
        List<PubPost> pubPosts = pubPostService.findPublished();
        if (CollectionUtils.isNotEmpty(pubPosts)) {
            User user = userService.findByUid(AuthContextHolder.getAuthUserDetails().getUid());
            List<PubPostRead> pubPostReads = pubPostReadService.findReaded(user, pubPosts);
            for (PubPost pubPost : pubPosts) {
                pubPost.addExtraAttribute("readed", false);
                for (PubPostRead pubPostRead : pubPostReads) {
                    if (pubPostRead.getPubPost().equals(pubPost)) {
                        pubPost.addExtraAttribute("readed", true);
                        break;
                    }
                }
            }
            setModel(pubPosts);
        }
        return buildDefaultHttpHeaders("list");
    }

    @Override
    @MetaData(title = "查看")
    public HttpHeaders view() {
        User user = userService.findByUid(AuthContextHolder.getAuthUserDetails().getUid());
        PubPostRead pubPostRead = pubPostReadService.findReaded(user, bindingEntity);
        if (pubPostRead == null) {
            pubPostRead = new PubPostRead();
            pubPostRead.setFirstReadTime(new Date());
            pubPostRead.setReadTotalCount(1);
            pubPostRead.setReadUser(user);
            pubPostRead.setPubPost(bindingEntity);

            if (bindingEntity.getReadUserCount() == null) {
                bindingEntity.setReadUserCount(1);
            } else {
                bindingEntity.setReadUserCount(bindingEntity.getReadUserCount() + 1);
            }
        } else {
            pubPostRead.setLastReadTime(new Date());
            pubPostRead.setReadTotalCount(pubPostRead.getReadTotalCount() + 1);
        }
        pubPostReadService.save(pubPostRead);
        pubPostService.save(bindingEntity);
        return buildDefaultHttpHeaders("view");
    }
}