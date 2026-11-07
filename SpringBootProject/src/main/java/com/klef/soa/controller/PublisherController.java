package com.klef.soa.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.klef.soa.entity.Publisher;
import com.klef.soa.service.PublisherService;

@RestController
@RequestMapping("/Publisher")
public class PublisherController {
	@Autowired
	private PublisherService service;

	@GetMapping("/")
	public String demo()
	{
		return "Spring Boot Project";
	}
	
	// Add Publisher
		@PostMapping("/add")
		public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher P)
		{
			Publisher publisher = service.addPublisher(P);
			return ResponseEntity.status(201).body(publisher);
		}
		
		// Display All Publishers
		@GetMapping("/displayall")
		public ResponseEntity<List<Publisher>> displayAllPublishers()
		{
			List<Publisher> publishers = service.displayallPublishers();
			return ResponseEntity.status(200).body(publishers);
		}
		
		// Display Publisher By ID
		@GetMapping("/display")
		public ResponseEntity<?> displaypublisherById(@RequestParam Long id)
		{
			Publisher publisher = service.displayById(id);

			if (publisher != null)
			{
				return ResponseEntity.status(200).body(publisher);
			}
			else
			{
				return ResponseEntity.status(404).body("Publisher ID Not Found");
			}
		}
		
		
		// Update Publisher
		@PutMapping("/update")
		public ResponseEntity<?> updatePublisher(@RequestBody Publisher publisher)
		{
			Publisher P = service.updatePublisher(publisher);

			if (P != null)
			{
				return ResponseEntity.ok(P);
			}
			else
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher ID Not Found");
			}
		}
		
		
		// Delete Publisher By ID
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteDoctorById(@PathVariable Long id)
		{
			String message = service.deletePublisherById(id);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
		@GetMapping("/displaybyname/{name}")
		public ResponseEntity<List<Publisher>> displayPublishersByName(@PathVariable String name) {
			return ResponseEntity.ok(service.FindByName(name));
		}

		@GetMapping("/displaybytype")
		public ResponseEntity<List<Publisher>> displayPublisherByType(@RequestParam String type) {
			return ResponseEntity.ok(service.findByType(type));
		}

}
