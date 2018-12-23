package application.model;

public class Cookie {
	private String account;
	
	private String password;
	
	private String status;
	
	public Cookie(String account,String password,String status) {
		this.account = account;
		this.password = password;
		this.status = status;
	}
	public String getAccount() {
		return this.account;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
