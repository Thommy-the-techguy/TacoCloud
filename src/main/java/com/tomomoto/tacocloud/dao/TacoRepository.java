package com.tomomoto.tacocloud.dao;

import com.tomomoto.tacocloud.taco.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Integer> {
}
