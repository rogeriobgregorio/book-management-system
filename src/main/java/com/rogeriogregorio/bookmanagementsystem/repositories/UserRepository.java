package com.rogeriogregorio.bookmanagementsystem.repositories;

import com.rogeriogregorio.bookmanagementsystem.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

   UserDetails findByLogin(String login);
}
