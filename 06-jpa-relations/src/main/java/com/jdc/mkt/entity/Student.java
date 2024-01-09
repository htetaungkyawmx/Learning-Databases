package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Embedded
	private Account account;
	
	@ElementCollection
	@CollectionTable(name = "course_tbl",
	joinColumns = @JoinColumn(unique = true,name = "student_id"))
	@Enumerated(EnumType.STRING)
	@Column(name ="course")
	//@OrderBy("course desc")
	@OrderColumn(name = "order_course")
	private List<Subject> courses;
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "mark")
	@MapKeyColumn(name = "mark_key")
	//@OrderBy("mark_key desc")
	@OrderColumn(name = "order_mark")
	
	@CollectionTable(name = "marks_tbl",joinColumns = @JoinColumn(name = "student_id"))
	private Map<Subject, String> marks;
	@ElementCollection
	//@OrderBy("mark desc")
	@OrderColumn(name = "order_grade")
	
	@CollectionTable(name = "grade_tbl",joinColumns = @JoinColumn(name = "student_id"))
    private Set<Subject> grade;
	
	public enum Subject{
		JAVA,SPRING
	}
}







