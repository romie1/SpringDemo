package com.example.springDemo.exercise1;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "TEAMS")
public class Team {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TeamGen")
    @SequenceGenerator(sequenceName = "TEAM_SEQ", allocationSize = 1, name = "TeamGen")
    @Column(name = "team_id")
    private long id;

    private String name;
    
    

//    @OneToOne(optional = false)
//    @JoinColumn(name="leader_id")
//    @JsonIgnoreProperties(value =  "leadingTeam", allowSetters = true)
    
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Coder leader;
    
    
    
//    @ManyToMany
//	@JoinTable(name = "team_coder", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "coder_id"))
//    @JsonIgnoreProperties("teams")
//	private Set<Coder> coders;

    
    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coder getLeader() {
		return leader;
	}

	public void setLeader(Coder leader) {
		this.leader = leader;
	}
    
    
	@Override
	public String toString() {
		return String.format("Team name = %s - Leader = %s", name, leader);
	}

//	public Set<Coder> getCoders() {
//		return coders;
//	}
//
//	public void setCoders(Set<Coder> coders) {
//		this.coders = coders;
//	}
    
    
}
