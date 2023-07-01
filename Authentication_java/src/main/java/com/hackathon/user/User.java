package com.hackathon.user;

public class User {
	private int userId;
	private String userName;
	private int roleId;
	private String roleName;
	private String updatedBy;
	public User(int userId, String userName, int roleId, String roleName, String updatedBy) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.updatedBy = updatedBy;
	}
	public User(String userName, int roleId, String roleName, String updatedBy) {
		super();
		this.userName = userName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.updatedBy = updatedBy;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", roleId=" + roleId + ", roleName=" + roleName
				+ ", updatedBy=" + updatedBy + "]";
	}

}