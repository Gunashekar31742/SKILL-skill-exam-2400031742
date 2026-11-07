package com.klef.soa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.klef.soa.entity.Publisher;
import com.klef.soa.repository.PublisherRepository;
import com.klef.soa.service.PublisherService;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:publisher-test;DB_CLOSE_DELAY=-1",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class PublisherServiceIntegrationTest {

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private PublisherRepository publisherRepository;

	@BeforeEach
	void clearPublishers() {
		publisherRepository.deleteAll();
	}

	@Test
	void testAddAndFindPublisherById() {
		Publisher saved = publisherService.addPublisher(createPublisher("Bright Press", "Book", true));

		Publisher found = publisherService.displayById(saved.getId());

		assertNotNull(found);
		assertEquals(saved.getId(), found.getId());
		assertEquals("Bright Press", found.getName());
	}

	@Test
	void testFindPublishersByName() {
		publisherService.addPublisher(createPublisher("Bright Press", "Book", true));

		List<Publisher> publishers = publisherService.FindByName("Bright Press");

		assertEquals(1, publishers.size());
		assertEquals("Bright Press", publishers.get(0).getName());
	}

	@Test
	void testFindPublishersByType() {
		publisherService.addPublisher(createPublisher("Bright Press", "Book", true));
		publisherService.addPublisher(createPublisher("Daily News", "Newspaper", true));

		List<Publisher> publishers = publisherService.findByType("Newspaper");

		assertEquals(1, publishers.size());
		assertEquals("Daily News", publishers.get(0).getName());
	}

	@Test
	void testUpdatePublisher() {
		Publisher saved = publisherService.addPublisher(createPublisher("Bright Press", "Book", true));
		Publisher update = createPublisher("Bright Press Updated", "Magazine", false);
		update.setId(saved.getId());

		Publisher updated = publisherService.updatePublisher(update);

		assertNotNull(updated);
		assertEquals("Bright Press Updated", updated.getName());
		assertEquals("Magazine", updated.getType());
		assertEquals(false, updated.getStatus());
	}

	@Test
	void testDeletePublisher() {
		Publisher saved = publisherService.addPublisher(createPublisher("Bright Press", "Book", true));

		String result = publisherService.deletePublisherById(saved.getId());

		assertEquals("Publisher Deleted Successfully", result);
		assertEquals(null, publisherService.displayById(saved.getId()));
	}

	@Test
	void testDisplayAllPublishers() {
		publisherService.addPublisher(createPublisher("Bright Press", "Book", true));

		List<Publisher> publishers = publisherService.displayallPublishers();

		assertEquals(1, publishers.size());
		assertEquals("Bright Press", publishers.get(0).getName());
	}

	private Publisher createPublisher(String name, String type, Boolean status) {
		Publisher publisher = new Publisher();
		publisher.setName(name);
		publisher.setType(type);
		publisher.setStatus(status);
		publisher.setLocation("Campus");
		return publisher;
	}
}
