


package pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	
	public String username;
	public String password;
	 
	public User(){}
	
	public User (String username, String userpass){
		this.username = username ;
		this.password = userpass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}



