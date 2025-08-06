package com.dilaraalk.service;

import com.dilaraalk.dto.DtoLoginRequest;
import com.dilaraalk.dto.DtoUserRegisterRequest;

public interface IAuthService {
	
	void register(DtoUserRegisterRequest request);
	
	String login(DtoLoginRequest request);

}
