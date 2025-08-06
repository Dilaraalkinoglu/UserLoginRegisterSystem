package com.dilaraalk.service;

import java.util.Optional;

import com.dilaraalk.entities.User;

public interface IUserService {
	
	Optional<User> findByUserName(String userName);

}
