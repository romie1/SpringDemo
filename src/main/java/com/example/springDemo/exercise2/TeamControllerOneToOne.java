package com.example.springDemo.exercise2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springDemo.exercise1.Team;
import com.example.springDemo.exercise1.TeamController;
import com.example.springDemo.exercise1.TeamNotFoundException;
import com.example.springDemo.exercise1.TeamRepository;

public class TeamControllerOneToOne {
	private static final Logger LOG = LoggerFactory.getLogger(TeamControllerOneToOne.class);
	
	@Autowired
	private TeamRepository repository;
	
	@GetMapping("/teams")
	public List<Team> getAll(){
	LOG.debug("enter getAll method");
		return repository.findAll();
	}
	
	@GetMapping("/teams/{id}")
	public Team get(@PathVariable Long id) {
		LOG.debug("enter get method");
		return repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
	}
	
	@PostMapping("/teams")
	public Team create(@RequestBody Team newTeam) {
		LOG.debug("enter create method");
		return repository.save(newTeam);
	}
	
	@PutMapping("/teams/{id}")
	public Team update(@RequestBody Team updatedTeam, @PathVariable Long id) {
		LOG.debug("enter update method");
		return repository.findById(id).map(team -> {
			team.setName(updatedTeam.getName());
			return repository.save(team);
		}).orElseGet(() -> {
			updatedTeam.setId(id);
            return repository.save(updatedTeam);
        });
	}
	
	@DeleteMapping("/teams/{id}")
	public void delete(@PathVariable Long id) {
		LOG.debug("enter delete method");
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException ex) {
            LOG.warn("Can't delete team", ex);
            throw new TeamNotFoundException(id);
        }catch(IllegalArgumentException iae) {
        	LOG.warn("Can't delete team", iae);
            throw new TeamNotFoundException(id);
        }
	}
}
