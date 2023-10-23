package ru.example.bootAndHibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.example.bootAndHibernate.entity.User;
import ru.example.bootAndHibernate.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
}
