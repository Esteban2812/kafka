package com.isaleesai.kafka;

public class serializer { 
	private int studentID; 
	private String studentName; 
	
	public serializer(int ID, String name) { 
		this. studentID = ID; 
		this. studentName = name; } 
	
	public int getID() { 
		return studentID; } 
	
	public String getName() { 
		return studentName; } 
}