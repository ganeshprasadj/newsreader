package newsreader;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Subscription {
	
	private String uname;
	private String subscription;
	private Users user;
	@Id
	public String getUname() {
		return uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getSubscription() {
		return subscription;
	}
	
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	
	

}
