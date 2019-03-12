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
@Table(name = "professor")
@XmlRootElement(name = "ProfTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prof {

	@Id @XmlElement(name = "username")
	private String username;
	
	 @OneToOne()
	 @JoinColumn(name="username")
	 private user_table user;

	public Prof(){
	}

	public Prof(String username) {
			this.username = username;
		}
	}
