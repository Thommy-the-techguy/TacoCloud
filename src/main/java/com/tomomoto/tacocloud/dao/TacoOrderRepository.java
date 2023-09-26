package com.tomomoto.tacocloud.dao;

import com.tomomoto.tacocloud.taco.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoOrderRepository extends CrudRepository<TacoOrder, Integer> {
}
