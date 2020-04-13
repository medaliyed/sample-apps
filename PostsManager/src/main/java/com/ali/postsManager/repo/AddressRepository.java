package com.ali.postsManager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ali.postsManager.entity.Address;
@Repository
public interface AddressRepository  extends JpaRepository<Address, Integer>{

	Address findByUserId(Integer userId);
}
