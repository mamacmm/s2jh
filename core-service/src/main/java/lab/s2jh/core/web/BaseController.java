package lab.s2jh.core.web;

import java.io.Serializable;
import java.util.Map;

import lab.s2jh.core.entity.BaseEntity;
import lab.s2jh.core.web.json.HibernateAwareObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.opensymphony.xwork2.inject.Inject;

@SuppressWarnings("all")
public abstract class BaseController<T extends BaseEntity<ID>, ID extends Serializable> extends
		PersistableController<T, ID> {

	protected long imageUploadMaxSize;

	@Inject(value = "struts.image.upload.maxSize", required = false)
	public void setImageUploadMaxSize(String imageUploadMaxSize) {
		this.imageUploadMaxSize = Long.parseLong(imageUploadMaxSize);
	}

	public Long getImageUploadMaxSize() {
		return imageUploadMaxSize;
	}

	public String convertToJson(Map<String, Serializable> dataMap) {
		try {
			Map<String, Object> displayMap = Maps.newLinkedHashMap();
			displayMap.put("", "");
			displayMap.putAll(dataMap);
			return HibernateAwareObjectMapper.getInstance().writeValueAsString(displayMap);
		} catch (JsonProcessingException e) {
			return "{\"\":\"ERROR\"}";
		}
	}

}
