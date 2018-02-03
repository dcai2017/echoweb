package echoweb.bean;

import java.util.List;

public class LoginRspBean {
	private String username;
	// message of login status
	private String statusMsg;
	// indicate login success or failure
	private boolean loginStatus;
	// the users found 
	private List<WebUserBean> users;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public List<WebUserBean> getUsers() {
		return users;
	}
	public void setUsers(List<WebUserBean> users) {
		this.users = users;
	}
	

}
