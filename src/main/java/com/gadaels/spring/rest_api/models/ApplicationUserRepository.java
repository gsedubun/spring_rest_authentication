package com.gadaels.spring.rest_api.models;

import org.springframework.data.jpa.repository.JpaRepository;;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUserName(String userName);
    
}