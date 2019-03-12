package LabAssistant;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@Data
@Entity
@Table(name = "user_table")
@XmlRootElement(name = "UserTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class user_table {
	
	
	@Id @XmlElement(name = "username")
	private String username;


	@XmlElement(name = "password")
	private String password;

	@XmlElement(name = "firstname")
	private String firstname;
	
	@XmlElement(name = "lastname")
	private String lastname;
	
	@XmlElement(name = "email")
	private String email;
	
	@XmlElement(name = "id")
	private int id;
	
	public user_table(){
	}
	// TA or Student
	public user_table(String username, String password, String firstname, String lastname, String Email, int id) {

		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = Email;
		this.id = id;
	}
	
	//Professor
	public user_table(String username, String password, String firstname, String lastname, String Email) {

		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = Email;
	}
	}
