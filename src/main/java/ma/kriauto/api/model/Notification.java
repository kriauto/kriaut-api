package ma.kriauto.api.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "api_notification")
public class Notification {
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator_notification")
	@SequenceGenerator(name="generator_notification",sequenceName="api_notification_sequence",allocationSize=1)
	private Long id;
	  
	@Column(name="carid")
	private Long carid;
	  
	@Column(name="message")
	private String message;
	  
	@Column(name = "creationdate", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp creationdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCarid() {
		return carid;
	}

	public void setCarid(Long carid) {
		this.carid = carid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Timestamp creationdate) {
		this.creationdate = creationdate;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", carid=" + carid + ", message="
				+ message + ", creationdate=" + creationdate + "]";
	}
}
