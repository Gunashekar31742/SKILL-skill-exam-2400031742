package com.klef.soa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="publisher_table")
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pid")
	
	private Long Id;
	@Column(name="pname",length = 30,nullable = false)
	private String name;
	@Column(name="ptype",length=50,nullable = false)
	private String type;
	@Column(name="pstatus",length=20,nullable = false)
	private Boolean status;
	//@Column(name="plocation",length=20,nullable=false)
	private String location;
	
	
	@UpdateTimestamp
	private LocalDateTime updatedat;
	@CreationTimestamp
	private LocalDateTime createdat;
}