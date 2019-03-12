package LabAssistant;

import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Data
@Entity
@Table(name = "student")
@XmlRootElement(name = "StudentTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

	@Id
	@XmlElement(name = "username")

	private String username;

	@OneToOne()
	@JoinColumn(name = "username")
	private user_table user;

	@XmlElement(name = "section")
	private int section;

	private static SessionFactory factory;

	public Student() {
		//setSection();
		int newsection = ((int) ((Math.random() * 8) + 1));
		this.setSection(newsection);
	}

	public Student(String username) {
		this.username = username;
		setSection();
		//int newsection = ((int) ((Math.random() * 8) + 1));
		//this.setSection(newsection);
	}

	public void setSection() {

		Session session = factory.openSession();
		Transaction tx = null;

		try {
			// Define a list of students and tas
			tx = session.beginTransaction();
			List<TA> taList = (List<TA>) session.createQuery("FROM TA").list();
			List<Student> studentList = (List<Student>) session.createQuery("FROM Student").list();
			Iterator iterator1 = taList.iterator();
	
			int count = Integer.MAX_VALUE;
			int temp;
			int section = 0;
			//find the lowest number of students in a section
			while (iterator1.hasNext()) {
					temp = 0;
					TA ta = (TA) iterator1.next();				
					Iterator iterator2 = studentList.iterator();
					
					while (iterator2.hasNext()) {
						Student student = (Student) iterator2.next();
						if (student.getSection() == ta.getSection())
							temp++;
					}
					if (temp < count) {
						count = temp;
						section = ta.getSection();
					}
			}
			
			this.setSection(section);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
