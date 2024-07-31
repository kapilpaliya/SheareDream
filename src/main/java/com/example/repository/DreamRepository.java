package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Dream;


public interface DreamRepository extends JpaRepository<Dream, Integer>{

}
