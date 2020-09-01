package com.paypal.bfs.test.employeeserv.api.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name="employee")
public class Employee {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date", columnDefinition = "DATETIME")
    private Date birthDate;
     
    @Column(name="FirstName")
    private String firstName;
    
    @Column(name="LastName")
    private String lastName;

     
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="addressid")
    private Address address;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
}