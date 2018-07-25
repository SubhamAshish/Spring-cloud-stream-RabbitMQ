package com.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class EmailModel {

	
	private String fromUserName;

	private String toUserName;

	private List<String> toEmailIds;

	private List<String> ccEmailIds;

	private String subject;

	private String message;

	
	
}
