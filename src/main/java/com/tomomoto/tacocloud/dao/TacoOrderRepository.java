package com.tomomoto.tacocloud.dao;

import com.tomomoto.tacocloud.taco.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Integer> {
}
