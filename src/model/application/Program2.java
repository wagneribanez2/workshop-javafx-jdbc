package model.application;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DepartmentDao departmentDao = new DepartmentDaoJDBC(DB.getConnection());
		
		System.out.println("==== Test 1: department findById ====");
		Department dep = departmentDao.finById(1);		
		System.out.println(dep);
		
		System.out.println("\n==== Test 2: department findAll ====");
		List<Department> list = departmentDao.findAll();
		for(Department d: list) {
			System.out.println(d);
		}
		
		/*
		System.out.println("\n==== Test 3: department insert ====");
		dep = new Department();
		dep.setName("Drink");
		departmentDao.insert(dep);
		System.out.println("Inserted! New id = " + dep.getId());
		*/
		
		System.out.println("\n==== Test 4: seller update ====");
		dep.setId(1);
		dep.setName("Computers");
		departmentDao.update(dep);
		System.out.println("Update completed!");
		
		
		System.out.println("\n==== Test 5: department delete ====");
		System.out.print("Enter id for delete test: ");
		int id = scan.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed!");
		
		
		scan.close();
	}
}
