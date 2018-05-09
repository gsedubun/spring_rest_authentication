package com.gadaels.spring.rest_api.user;

import java.util.List;

import com.gadaels.spring.rest_api.Application;
import com.gadaels.spring.rest_api.controllers.UserController;
import com.gadaels.spring.rest_api.models.ApplicationUser;
import com.gadaels.spring.rest_api.models.ApplicationUserRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private ApplicationUserRepository applicationUserRepository;
	public UserDetailsServiceImpl(ApplicationUserRepository _ApplicationUserRepository) {
        this.applicationUserRepository = _ApplicationUserRepository;
    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUserName(username);
        if(applicationUser==null){
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
	}

}