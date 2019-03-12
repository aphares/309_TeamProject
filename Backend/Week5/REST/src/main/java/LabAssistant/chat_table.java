package LabAssistant;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@Data
@Entity
@Table(name = "ChatTable")
@XmlRootElement(name = "chatData")
@XmlAccessorType (XmlAccessType.FIELD)
public class chat_table {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@XmlElement(name = "netid")
	private String netid;
	
	@XmlElement(name = "message")
	private String message;

	@XmlElement(name = "timestamp")
	private String timestamp;
	
	public chat_table(){
		setTime();
	}
	
	public chat_table(String username, String chattext) {

		setTime();
		this.netid = username;
		this.message = chattext;
	}
	
	public void setTime() {
	    DateFormat df = new SimpleDateFormat("HH:mm");
	    Date dateobj = new Date();
	    timestamp = df.format(dateobj);
	    if (Integer.valueOf(timestamp.substring(0,2)) > 12) {
	    	timestamp = String.valueOf(Integer.valueOf(timestamp.substring(0,2)) - 12) + timestamp.substring(2) + " PM";
	    }
	    else if (Integer.valueOf(timestamp.substring(0,2)) == 12) {
	    	timestamp+= " PM";
	    }
	    else if (Integer.valueOf(timestamp.substring(0,2)) == 0) {
	    	timestamp = "12" + timestamp.substring(2) + " AM";
	    }
	    else {
	    	timestamp+= " AM";
	    }
	}
}