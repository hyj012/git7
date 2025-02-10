package com.itwillbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwillbs.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	
//	insert into customer(name) values("kim");
//	insert into Food(name,price,customer_id) values("라면",1000,1);
	
//	Optional<Food> food = foodRepository.findById(1L);
	
	
//    select
//    	f1_0.id,
//    	c1_0.id,
//    	c1_0.name,
//    	f1_0.name,
//    	f1_0.price 
//	  from
//    	food f1_0 
//    left join
//    	customer c1_0 
//        	on c1_0.id=f1_0.customer_id 
//	  where
//    	f1_0.id=?
	
}
