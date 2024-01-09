package com.jdc.mkt.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jdc.mkt.listener.EnableTimesListener;
import com.jdc.mkt.listener.Times;
import com.jdc.mkt.listener.TimesListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import static javax.persistence.FetchType.EAGER;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "category_tbl")
//@EntityListeners(TimesListener.class)
public class Category implements EnableTimesListener{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column(length = 45,nullable = false)
	private String name;
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
	
	private Times times;
	
	@ElementCollection
	@CollectionTable(name ="tags_tbl",
	joinColumns = @JoinColumn(name = "category_id"))
	private List<String>tags;
	
	@OneToMany(mappedBy = "category",
			cascade = {
					CascadeType.PERSIST,
					CascadeType.REMOVE
					})
	private List<Product> products;
	
	{
		tags = new ArrayList<String>();
		products = new ArrayList<Product>();
	}
	
	public void addTag(String...tag) {
		tags.addAll(List.of(tag));
	}
	
	public void addProduct(Product p) {
		p.setCategory(this);
		products.add(p);
	}
	
	
	
	
	
	
	
	
	
}
