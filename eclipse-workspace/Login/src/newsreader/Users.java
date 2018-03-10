package newsreader;

//package newsreader;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
	
	private String name;
	private String uname;
	private String newspaper_list;
	private String password;
	
	@Id
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewspaper_list() {
		return newspaper_list;
	}
	
	public void setNewspaper_list(String newspaper_list) {
		this.newspaper_list = newspaper_list;
	}

}

