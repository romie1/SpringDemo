package com.example.springDemo.exercise1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springDemo.exercise2.CoderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TeamController {
	private static final Logger LOG = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private CoderRepository coderRepository;
	
	@GetMapping("/teams")
	public List<Team> getAll(){
	LOG.debug("enter getAll method");
		return teamRepository.findAll();
	}
	
	@GetMapping("/teams/{id}")
	public Team get(@PathVariable Long id) {
		LOG.debug("enter get method");
		return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
	}
	
	@PostMapping("/teams")
	public Team create(@RequestBody Team newTeam) {
		LOG.debug("enter create method");
		return teamRepository.save(newTeam);
	}
	
	@PutMapping("/teams/{id}")
	public Team update(@RequestBody Team updatedTeam, @PathVariable Long id) {
		LOG.debug("enter update method");
		return teamRepository.findById(id).map(team -> {
			team.setName(updatedTeam.getName());
			team.setLeader(coderRepository.findById(id).get());
			return teamRepository.save(team);
		}).orElseGet(() -> {
			updatedTeam.setId(id);
            return teamRepository.save(updatedTeam);
        });
	}
	
	@DeleteMapping("/teams/{id}")
	public void delete(@PathVariable Long id) {
		LOG.debug("enter delete method");
		try {
			teamRepository.deleteById(id);
		}catch (EmptyResultDataAccessException ex) {
            LOG.warn("Can't delete team", ex);
            throw new TeamNotFoundException(id);
        }catch(IllegalArgumentException iae) {
        	LOG.warn("Can't delete team", iae);
            throw new TeamNotFoundException(id);
        }
	}
	
	@GetMapping("/teams/byName/{name}")
	public Team getByName(@PathVariable String name) {
		LOG.debug("enter get method");
		return teamRepository.findByName(name).orElseThrow(() -> new TeamNotFoundException(name));
	}
	
}
