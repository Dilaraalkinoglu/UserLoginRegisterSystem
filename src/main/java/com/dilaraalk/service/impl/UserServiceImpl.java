package com.dilaraalk.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dilaraalk.entities.User;
import com.dilaraalk.repository.UserRepository;
import com.dilaraalk.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{
	
	private final UserRepository userRepository;
	
	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
