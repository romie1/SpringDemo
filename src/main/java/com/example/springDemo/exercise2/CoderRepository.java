package com.example.springDemo.exercise2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springDemo.exercise1.Coder;

@Repository
public interface CoderRepository extends JpaRepository<Coder, Long> {
}
