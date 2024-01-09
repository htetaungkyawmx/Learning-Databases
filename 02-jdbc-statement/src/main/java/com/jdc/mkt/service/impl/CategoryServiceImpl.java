package com.jdc.mkt.service.impl;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.service.CategoryService;
import static com.jdc.mkt.utils.SqlConnection.getConnection;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public int insertCategory(String name) {
		String sql = "insert into category_tbl (name) values ('%s')".formatted(name.toLowerCase());
		try (var con = getConnection(); var stmt = con.createStatement()) {
			return stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCategory(String name, boolean active, int id) {
		String sql = "update category_tbl set name='%s',active=%d where id = %d"
				.formatted(name.toLowerCase(),active?1:0,id);
		try (var con = getConnection(); 
				var stmt = con.createStatement()) {
			
			return stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Category findById(int id) {
		
		String sql = "select * from category_tbl where id = %d".formatted(id);
		try (var con = getConnection(); 
				var stmt = con.createStatement()) {
			
			var rs = stmt.executeQuery(sql);
			while(rs.next()) {
				var cat = new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setActive(rs.getBoolean("active"));
				return cat;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void clearCategory() {
		String sql = "truncate table category_tbl";
		try (var con = getConnection(); 
				var stmt = con.createStatement()) {
			
			 stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
