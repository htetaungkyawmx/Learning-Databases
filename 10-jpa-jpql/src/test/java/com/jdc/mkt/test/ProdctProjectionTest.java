package com.jdc.mkt.test;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.mkt.entity.dto.PrdouctWithSdTotalByCatNameDto;
import com.jdc.mkt.entity.dto.ProductByTownshipAndBtwDate;
import com.jdc.mkt.entity.dto.ProductCountByCategoryDto;
import com.jdc.mkt.entity.dto.QtyBySaleDateDto;

@TestMethodOrder(OrderAnnotation.class)
public class ProdctProjectionTest extends JPAFactory{
	
	@Test
	@Order(1)
	void selectProductCountByCategory() {
		var query = em.createQuery("""
				select new com.jdc.mkt.entity.dto.ProductCountByCategoryDto(c.name,count(p))
				from Product p join p.category c group by c.name
				""" ,ProductCountByCategoryDto.class);
		var list = query.getResultList();
		list.forEach(p -> System.out.println("Category :%s\tCount :%d".formatted(p.cName(),p.count())));
	}
	
	@Test
	@Order(2)
	void selectProductByCategoryNameInSaleDetails() {
		var query = em.createQuery("""
				select new com.jdc.mkt.entity.dto.PrdouctWithSdTotalByCatNameDto(
				p.name,p.dtPrice,sum(sd.qty),sum(p.dtPrice*sd.qty))
				from SaleDetails sd
				join sd.product p
				where p.category.name = :name 
				group by p.name,p.dtPrice
				""",PrdouctWithSdTotalByCatNameDto.class);
			query.setParameter("name", "Diary");
			var list = query.getResultList();
			list.forEach(p -> 
			System.out.println("Name :%s\tprice :%d\tqty :%d\t total :%d"
					.formatted(p.name(),p.price(),p.qty(),p.total())));
	}
	
	@Test
	@Order(3)
	void selectProductQtyBySaleDate() {
		var query = em.createQuery("""
				select new com.jdc.mkt.entity.dto.QtyBySaleDateDto(
				sd.sales.saleDate,sum(sd.qty) )
				from SaleDetails sd
				group by sd.sales.saleDate
				
				""",QtyBySaleDateDto.class);
		var list = query.getResultList();
		list.forEach(p -> System.out.println("date :%s\t qty:%d".formatted(p.ldt(),p.qty())));
	}
	
	@Test
	@Order(4)
	void selectProductByTownshipAndDate() {
		var query = em.createQuery("""
				select new com.jdc.mkt.entity.dto.ProductByTownshipAndBtwDate(
				sd.product.name,sd.qty)
				from SaleDetails sd where
				sd.sales.customer.address.township = :township and
				sd.sales.saleDate between :from and :to
				""",ProductByTownshipAndBtwDate.class);
		query.setParameter("township", "Hlaing");
		query.setParameter("from",LocalDateTime.of(2022, 01, 12, 0, 0));
		query.setParameter("to",LocalDateTime.of(2022, 04,05,0,0,0));
		var list = query.getResultList();
		list.forEach(p -> System.out.println("Name :%s\t Qty :%d".formatted(p.name(),p.qty())));
	}
	

}
