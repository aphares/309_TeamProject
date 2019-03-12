package LabAssistant;

import lombok.Data;

import java.util.*;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
@Table(name = "ta")
@XmlRootElement(name = "TATable")
@XmlAccessorType(XmlAccessType.FIELD)
public class TA {
	
	@Id @XmlElement(name = "username")
	private String username;
	
	 @OneToOne()
	 @JoinColumn(name="username")
	 private user_table user;
	 
	@XmlElement(name = "section")
	private int section;
	
	 
	public TA(){
		int newsection = ((int)((Math.random()*8)+1));
	    this.setSection(newsection);

	}

	public TA(String username) {
	    this.username = username;
		int newsection = ((int)((Math.random()*8)+1));
	    this.setSection(newsection);
	}
	
	
	public void setSection() {
		
		/*
		int tmpsection = rand.nextInt(99); 
		this.user.setSection(tmpsection);
	
	      try {
	         tx = session.beginTransaction();
	         List<TA> tas = (List<TA>) session.createQuery("FROM TA").list(); 
	 	  int c = 1;
	      while (c < 10) { 
	         for (Iterator iterator1 = tas.iterator(); iterator1.hasNext();){
	            TA ta = (TA) iterator1.next();
	            if (tmpsection == ta.user.getSection()) {
	            	tmpsection = 100;
	            	break;
	            }
	         }
	         if (tmpsection != 100) break;
	         tmpsection = rand.nextInt(99); 
	      c++;
	      }
	      this.user.setSection(tmpsection);
	         
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	*/	
	}

}


