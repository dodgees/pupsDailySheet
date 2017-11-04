package com.mindcanary.infrastructure;

public class Persisted {

	private Long id;

	private Long insertTime;

	private Long updateTime;

	private Long insertBy;

	private Long updateBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Long insertTime) {
		this.insertTime = insertTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(Long insertBy) {
		this.insertBy = insertBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

}
