package com.example.service;

import java.util.List;

import com.example.entity.Dream;


public interface DreamService {

	public Dream saveDream(Dream dream);
	
	public List<Dream> getAll();
	
}
