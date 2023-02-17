package com.slb.SpringSecurityDemo.Repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slb.SpringSecurityDemo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByUserName(String userName);
}
