package cn.proem.dagl.web.flow.entity;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import cn.proem.dagl.web.table.entity.inf.BaseEntityInf;
import cn.proem.suw.web.common.model.MappedEntityModel;

/**
 * Java基本数据类型
 *  
 * @author tangcc
 *
 */
@MappedSuperclass
public class FileEntity extends MappedEntityModel implements BaseEntityInf {
	/**
	 * 资料id
	 */
	@Column
	private String dataId;

	/**
	 * 是否归档 0
	 */
	@Column
	private Integer isarchive = 0;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Integer getIsarchive() {
		return isarchive;
	}

	public void setIsarchive(Integer isarchive) {
		this.isarchive = isarchive;
	}

	public void set(String colname, Object val) {
		Field field;
		try {
			field = this.getClass().getDeclaredField(colname);
			field.setAccessible(true);
			field.set(this, val);
		} catch (NoSuchFieldException e1) {
			try {
				field = this.getClass().getSuperclass()
						.getDeclaredField(colname);
				field.setAccessible(true);
				field.set(this, val);
			} catch (NoSuchFieldException e) {
				try {
					field = this.getClass().getSuperclass().getSuperclass()
							.getDeclaredField(colname);
					field.setAccessible(true);
					field.set(this, val);
				} catch (NoSuchFieldException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SecurityException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalArgumentException e2) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e2) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String colname) {
		Field field;
		try {
			field = this.getClass().getDeclaredField(colname);
			field.setAccessible(true);
			return (T) field.get(this);
		} catch (NoSuchFieldException e1) {
			try {
				field = this.getClass().getSuperclass()
						.getDeclaredField(colname);
				field.setAccessible(true);
				return (T) field.get(this);
			} catch (NoSuchFieldException e) {
				try {
					field = this.getClass().getSuperclass().getSuperclass()
							.getDeclaredField(colname);
					field.setAccessible(true);
					return (T) field.get(this);
				} catch (NoSuchFieldException | SecurityException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalArgumentException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalAccessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}