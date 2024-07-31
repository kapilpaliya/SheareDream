package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Dream;
import com.example.repository.DreamRepository;

@Service
public class DreamServiceImp implements DreamService {

	@Autowired
	DreamRepository dreamRepository;

	@Override
	public Dream saveDream(Dream dream) {
		return dreamRepository.save(dream);
	}

	@Override
	public List<Dream> getAll() {
		return dreamRepository.findAll();
	}

}
