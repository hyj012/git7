package com.itwillbs.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "customer")
	private List<Food> foodList = new ArrayList<>();
	
	
//	Hibernate: 
//	    create table customer (
//	        id bigint not null auto_increment,
//	        name varchar(255),
//	        primary key (id)
//	    ) engine=InnoDB
//	Hibernate: 
//	    create table food (
//	        id bigint not null auto_increment,
//	        name varchar(255),
//	        price float(53) not null,
//	        customer_id bigint,
//	        primary key (id)
//	    ) engine=InnoDB
//	Hibernate: 
//	    alter table food 
//	       add constraint FKew3kwugrx7vav3w4cowvb9h9g 
//	       foreign key (customer_id) 
//	       references customer (id)
	
//	연관관계매핑
//	일대일(1:1) : @OneToOne  단방향
//	일대다(1:N) : @OneToMany 양방향 
//	다대일(N:1) : @ManyToOne  단방향, 양방향
//	다대다(N:M) : @ManyToMany
	
		
	       

}
