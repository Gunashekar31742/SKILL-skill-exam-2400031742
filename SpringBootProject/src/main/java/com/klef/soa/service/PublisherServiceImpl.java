package com.klef.soa.service;

import java.util.List;
import java.util.Optional;
import com.klef.soa.entity.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.klef.soa.repository.PublisherRepository;


@Service
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
     private PublisherRepository repo;
	@Override
	public Publisher addPublisher(Publisher P) {
	
		return repo.save(P);
	}

	@Override
	public Publisher updatePublisher(Publisher p)
	{
	    Optional<Publisher> optional = repo.findById(p.getId());

	    if(optional.isPresent())
	    {
	        Publisher publisher = optional.get();

	        publisher.setName(p.getName());
	        publisher.setType(p.getType());
	        publisher.setLocation(p.getLocation());
	        publisher.setStatus(p.getStatus());

	        return repo.save(publisher);
	    }

	    return null;
	}
	@Override
	public List<Publisher> displayallPublishers() {
		return repo.findAll();
		
	}

	@Override
	public String deletePublisherById(Long Id) {
		boolean flag = repo.existsById(Id);
		if(flag)
		{
			repo.deleteById(Id);
			return "Publisher Deleted Successfully";
		}
		else {
			return "Publisher ID not Found";
		}
	
	}

	@Override
	public Publisher displayById(Long Id) {
		return repo.findById(Id).orElse(null);
		
	}

	@Override
	public List<Publisher> FindByName(String name) {
		// TODO Auto-generated method stuba
		return repo.findByName(name);
	}

	@Override
	public List<Publisher> findByType(String type) {
		
		return repo.findByType(type);
	}
	
	
	
	
	

}
