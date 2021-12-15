package com.pragma.restapifull.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Data
@Entity
@Table (name="persons")
public class Person implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;


	@Column(name="name")
	private String name;


	@Column(name="lastname")
	private String lastName;

	@Column(name="image")
	private String image;

	@Column(name="age")
	private int age;


	@Column(name="city")
	private String city;


	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;


	@Column(name="update_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updateAt = new Date();
	}
	private static final long serialVersionUID = 1L;

}
