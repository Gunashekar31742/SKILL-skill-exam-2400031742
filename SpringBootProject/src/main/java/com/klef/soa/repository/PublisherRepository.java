package com.klef.soa.repository;

import java.util.List;
import com.klef.soa.entity.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	List<Publisher> findByName(String Name);
	List<Publisher> findByType(String type);

}
