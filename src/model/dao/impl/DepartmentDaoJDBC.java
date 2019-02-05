package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao{
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) {
		PreparedStatement ps = null;		

		try {
			ps = conn.prepareStatement(
					"INSERT INTO department (Name) " +					
					"VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);						
			
			ps.setString(1, obj.getName());			
			
			int rowsAffected = ps.executeUpdate();			
			
			if(rowsAffected > 0) {
				ResultSet rs1 = ps.getGeneratedKeys();
				if(rs1.next()) {
					int id = rs1.getInt(1);					
					obj.setId(id);
				}
				DB.closeResultSet(rs1);
			}else {
				throw new DbException("Unexpected error! No rows inserted! ");
			}			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);			
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"UPDATE department " + 
					"SET Name = ? " +  
					"WHERE Id = ? ");				
			
			ps.setString(1, obj.getName());			
			ps.setInt(2, obj.getId());			
			
			ps.executeUpdate();			
						
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);			
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;		

		try {
			ps = conn.prepareStatement(
					"DELETE FROM department " + 
					"WHERE Id = ? ");				
			
			ps.setInt(1, id);			
			ps.executeUpdate();			
						
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(ps);			
		}
		
	}

	@Override
	public Department finById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT * FROM DEPARTMENT " +
					"WHERE Id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department dep = new Department(rs.getInt("id"), rs.getString("Name"));
				return dep;
			}

			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM DEPARTMENT ORDER BY NAME");			

			rs = ps.executeQuery();

			List<Department> list = new ArrayList<>();			

			while (rs.next()) {
				Department dep = new Department(rs.getInt("id"), rs.getString("Name"));
				
				list.add(dep);
			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
