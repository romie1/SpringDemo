package com.example.springDemo.exercise1;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "CODERS")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
property = "id")
public class Coder {
   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CodGen")
    @SequenceGenerator(sequenceName = "CODER_SEQ", allocationSize = 1, name = "CodGen")
    @Column(name = "coder_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    private Double salary;
    
    @OneToOne(optional = true,  mappedBy = "leader")
    @JsonIgnoreProperties({"coders", "leader"})
    private Team leadingTeam;
    
   
//    @ManyToMany(mappedBy = "coders")
//    @JsonIgnoreProperties("teams")
//    private Set<Team> teams;

    public Coder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    
	public Team getLeadingTeam() {
		return leadingTeam;
	}

	public void setLeadingTeam(Team leadingTeam) {
		this.leadingTeam = leadingTeam;
	}
    

	@Override
	public String toString() {
		return String.format("Coder id = %s, First Name = %s, Last Name = %s, leading team = %s", id, firstName,lastName, leadingTeam);
	}

//	public Set<Team> getTeams() {
//		return teams;
//	}
//
//	public void setTeams(Set<Team> teams) {
//		this.teams = teams;
//	}
    
    
}
