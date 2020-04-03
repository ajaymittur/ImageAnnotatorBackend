package dev.cse.imageannotatorbackend.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Annotators {

	@Id
	@Column(name = "Username")
	private String username;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@Column(name = "Name")
	private String name;

	@Column(name = "Num_Images_Annotated")
	private long num_images_annotated;

	@Column(name = "Created_On")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_on;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNum_images_annotated() {
		return num_images_annotated;
	}

	public void setNum_images_annotated(long num_images_annotated) {
		this.num_images_annotated = num_images_annotated;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
}
