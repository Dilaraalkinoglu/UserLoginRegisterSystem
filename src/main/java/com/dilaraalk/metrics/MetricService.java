package com.dilaraalk.metrics;



import org.springframework.stereotype.Service;

import com.dilaraalk.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetricService {
	
	private final UserRepository userRepository;
	
	public long getTotalUserCount() {
		return userRepository.count();
	}
	

}
