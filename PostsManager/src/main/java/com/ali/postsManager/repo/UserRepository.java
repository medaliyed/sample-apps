package com.ali.postsManager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ali.postsManager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
