package com.ssm.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("tbl_sys_dict")
@SuppressWarnings("serial")
public class SysDict extends BaseModel {
	
	@TableField("dict_type")
	private String dictType;
	
	@TableField("dict_key")
	private String dictKey;
	
	@TableField("dict_value")
	private String dictValue;
	
    @TableField("parent_type")
    private String parentType;
    
    @TableField("parent_key")
    private String parentKey;
	
	@TableField("sort_no")
	private Integer sortNo;
	
	@TableField("editable")
	private Boolean editable;
	
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

    public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", dictType=").append(dictType);
		sb.append(", dictKey=").append(dictKey);
		sb.append(", dictValue=").append(dictValue);
		sb.append(", sortNo=").append(sortNo);
		sb.append(", editable=").append(editable);
		sb.append("]");
		return sb.toString();
	}

	/**
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		SysDict other = (SysDict) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getDictType() == null ? other.getDictType() == null : this.getDictType().equals(other.getDictType()))
				&& (this.getDictKey() == null ? other.getDictKey() == null : this.getDictKey().equals(other.getDictKey()))
				&& (this.getDictValue() == null ? other.getDictValue() == null
						: this.getDictValue().equals(other.getDictValue()))
				&& (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
				&& (this.getEditable() == null ? other.getEditable() == null
						: this.getEditable().equals(other.getEditable()))
				&& (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
				&& (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
				&& (this.getCreateBy() == null ? other.getCreateBy() == null
						: this.getCreateBy().equals(other.getCreateBy()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateBy() == null ? other.getUpdateBy() == null
						: this.getUpdateBy().equals(other.getUpdateBy()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null
						: this.getUpdateTime().equals(other.getUpdateTime()));
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getDictType() == null) ? 0 : getDictType().hashCode());
		result = prime * result + ((getDictKey() == null) ? 0 : getDictKey().hashCode());
		result = prime * result + ((getDictValue() == null) ? 0 : getDictValue().hashCode());
		result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
		result = prime * result + ((getEditable() == null) ? 0 : getEditable().hashCode());
		result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
		result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}
}
