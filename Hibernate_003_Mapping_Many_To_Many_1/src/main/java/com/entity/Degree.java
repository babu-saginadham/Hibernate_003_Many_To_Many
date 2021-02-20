package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEGREE")
public class Degree {

	@Id
    @Column(name = "degree_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int degree_id;
 
    @Column(name = "degree_name")
    private String degree_name;
    
    @ManyToMany(mappedBy = "degrees")
    private Set<Employee> employees = new HashSet();

	public int getDegree_id() {
		return degree_id;
	}

	public void setDegree_id(int degree_id) {
		this.degree_id = degree_id;
	}

	public String getDegree_name() {
		return degree_name;
	}

	public void setDegree_name(String degree_name) {
		this.degree_name = degree_name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
    
   
    
}
