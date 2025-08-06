package com.dilaraalk.service.impl;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.dilaraalk.repository.UserRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Veritabanından kullanıcıyı bul
        return userRepository.findByUserName(username)
                .map(user -> new User(
                        user.getUserName(), // username
                        user.getPassword(), // şifre (hashed)
                        user.getRoles().stream() // roller → SimpleGrantedAuthority'e çevir
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));
    }
}
