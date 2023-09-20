package com.tomomoto.tacocloud.dao;

import com.tomomoto.tacocloud.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    ArrayList<User> findUserByUsername(String username);
}
