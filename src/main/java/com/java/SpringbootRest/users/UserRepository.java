package com.java.SpringbootRest.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.SpringbootRest.post.Post;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
