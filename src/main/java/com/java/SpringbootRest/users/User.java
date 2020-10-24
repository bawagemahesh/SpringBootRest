package com.java.SpringbootRest.users;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.java.SpringbootRest.post.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Info about USer")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should have at least 2")
	@ApiModelProperty("Name should have at least 2")
	String name;
	
	@Past
	@ApiModelProperty("Birth Date should be in the Past only")
	private Date dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	
	public User() {
		
	}
	
	public User(String name, Integer id, Date dob) {
		super();
		this.name = name;
		this.id = id;
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", posts=" + posts + "]";
	}
	
}
