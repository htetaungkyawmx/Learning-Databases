package com.jdc.mkt.service.impl;

import static com.jdc.mkt.utils.MysqlConnection.getConnection;

import java.sql.Statement;
import java.util.Arrays;

import com.jdc.mkt.dto.ProductCountByCategory;
import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;
import com.jdc.mkt.entity.Product.Size;
import com.jdc.mkt.service.ProductCountByCategoryService;
import com.jdc.mkt.service.ProductService;

public class ProductServiceImpl implements ProductService,ProductCountByCategoryService {
	
	private Product[]products;
	private  Object[] objs;
	private ProductCountByCategory[]dto;
	
	public ProductServiceImpl() {
		products = new Product[0];
		objs = new Object[0];
		dto = new ProductCountByCategory[0];
	}
	
	
	
	private void addProduct(Product p) {
		products = Arrays.copyOf(products, products.length+1);
		products[products.length-1] = p;
	}
	
	private void addObj(Object obj) {
		objs = Arrays.copyOf(objs, objs.length+1);
		objs[objs.length-1] = obj;
	}
	private void addProductDto(ProductCountByCategory p) {
		dto = Arrays.copyOf(dto, dto.length+1);
		dto[dto.length-1] = p;
	}

	@Override
	public int insert(Product product) {
		String sql = "insert into product_tbl (name,price,size,category_id) values (?,?,?,?)";
		try (var con = getConnection(); var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, product.getName());
			stmt.setDouble(2, product.getPrice());
			stmt.setString(3, product.getSize().name());
			stmt.setInt(4, product.getCatgory().getId());
			stmt.executeUpdate();

			var rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product[] select(Product product) {
		StringBuilder sb = new StringBuilder(
				 """
				select p.id,p.name,p.price,p.size,c.id,c.name from product_tbl p 
				join category_tbl c on p.category_id = c.id 
				where p.active=1
				"""
				);
		
		if(null != product.getName()) {
			sb.append(" and lower(p.name) like lower(?)");
			addObj(product.getName().concat("%"));
		}
		
		if( null != product.getPrice() && product.getPrice() > 0) {
			sb.append(" and p.price = ?");
			addObj(product.getPrice());
		}
		
		if(null != product.getSize()) {
			sb.append(" and p.size = ?");
			addObj(product.getSize().name());
		}
		
		if(null != product.getCatgory() && null != product.getCatgory().getName()) {
			sb.append(" and lower(c.name) = lower(?)");
			addObj(product.getCatgory().getName());
		}
		
		System.out.println(sb.toString());
		System.out.println("Objs size "+ objs.length);
		
		try (var con = getConnection(); 
				var stmt = con.prepareStatement(sb.toString())) {
			
			for(int i = 0 ;i <objs.length ; i++) {
				stmt.setObject(i+1, objs[i]);
			}
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var c = new Category();
				c.setId(rs.getInt("c.id"));
				c.setName(rs.getString("c.name"));
				
				var p = new Product();
				p.setId(rs.getInt("p.id"));
				p.setName(rs.getString("p.name"));
				p.setPrice(rs.getDouble("p.price"));
				p.setSize(Size.valueOf(rs.getString("p.size")));
				p.setCatgory(c);
				addProduct(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void clear() {
		String sql = "truncate table product_tbl";
		try (var con = getConnection(); var stmt = con.prepareStatement(sql)) {
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	@Override
	public ProductCountByCategory[] selectCount() {
		String sql = """
				select c.name,count(p.name) count
				from product_tbl p join category_tbl c on p.category_id = c.id 
				group by c.name
				""";
		try (var con = getConnection(); var stmt = con.prepareStatement(sql)) {
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var d = new ProductCountByCategory(
						rs.getString("c.name"),
						rs.getInt("count"));
				addProductDto(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	

}
