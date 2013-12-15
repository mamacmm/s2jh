package lab.s2jh.biz.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lab.s2jh.core.annotation.MetaData;
import lab.s2jh.core.entity.PersistableEntity;
import lab.s2jh.core.entity.annotation.EntityAutoCode;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "SYS_ENUM_TYPE")
@MetaData(value = "数据字典类型")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class EnumType extends PersistableEntity<String> {
	private static final long serialVersionUID = 8915222070232166401L;

	@MetaData(value = "枚举类型", description = "填写代码表的表名，如：XX_JBXX")
    @EntityAutoCode(order = 10, search = true)
    private String enumType;

    @MetaData(value = "描述", description = "填写代码表的中文描述，如：学校类别")
    @EntityAutoCode(order = 20, search = true)
    private String description;

    @MetaData(value = "分类", description = "1.国家标准  2.教育部标准  3.系统定义  4.用户定义")
    @EntityAutoCode(order = 30, search = true)
    private String category;

    @MetaData(value = "使用状态", description = "1：使用  0：不使用")
    @EntityAutoCode(order = 70, search = true)
    private Boolean enabled = Boolean.TRUE;

    private String id;

    @Id
    @Column(length = 40)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Type(type = "lab.s2jh.biz.core.hib.BooleanStrUserType")
    @Column(nullable = false, length = 1, name = "status")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    @Transient
    public String getDisplay() {
        return enumType;
    }

    @Column(nullable = false, length = 60, unique = true)
    public String getEnumType() {
        return enumType;
    }

    public void setEnumType(String enumType) {
        this.enumType = enumType;
    }

    @Column(nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, length = 10)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public static enum EnumTypes{
        @MetaData(value = "性别")
        ZD_GB_XBM,
        
        @MetaData(value = "民族")
        ZD_GB_MZM,
        
        @MetaData(value = "国籍/地区")
        ZD_GB_GJDQM,
        
        @MetaData(value = "政治面貌")
        ZD_BB_ZZMMM,
        
        @MetaData(value = "健康状况")
        ZD_GB_JKZKM,
        
        @MetaData(value = "港澳台侨外码")
        ZD_BB_GATQWM,
        
        @MetaData(value = "身份证件类型码")
        ZD_BB_SFZJLXM
    }
}
