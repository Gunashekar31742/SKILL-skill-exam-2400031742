package com.klef.soa.service;

import java.util.List;
import com.klef.soa.entity.Publisher;


public interface PublisherService {
	Publisher addPublisher(Publisher P);
	Publisher updatePublisher(Publisher P);
	List<Publisher> displayallPublishers();
	String deletePublisherById(Long Id);
	Publisher displayById(Long Id);
	List<Publisher> FindByName(String name);
	List<Publisher> findByType(String type);
	
	
	
	

}
