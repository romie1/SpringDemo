package com.example.springDemo.exercise1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	
	public Optional<Team> findByName(String name);
}
