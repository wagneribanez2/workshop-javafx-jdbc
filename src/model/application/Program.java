package model.application;


import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) throws ParseException {
		Scanner scan = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("==== Test 1: seller findById ====");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		System.out.println("\n==== Test 2: seller findByDepartment ====");
		Department dep = new Department(2, null);		
		List<Seller> list = sellerDao.findByDepartment(dep);		
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== Test 3: seller findAll ====");		
		List<Seller> list2 = sellerDao.findAll();		
		for(Seller obj : list2) {
			System.out.println(obj);
		}
		
		/*
		System.out.println("\n==== Test 4: seller insert ====");
		Seller seller2 = new Seller();
		seller2.setName("Wagner");
		seller2.setEmail("wagner@hotmail.com");
		seller2.setBaseSalary(3000);
		seller2.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/11/1989"));
		seller2.setDepartment(dep);
		sellerDao.insert(seller2);
		System.out.println("Inserted! New id = " + seller2.getId());
		*/
		
		System.out.println("\n==== Test 5: seller update ====");
		Seller seller2 = sellerDao.findById(1);		
		seller2.setName("Martha Wayne");
		sellerDao.update(seller2);
		System.out.println("Update completed!");
		
		System.out.println("\n==== Test 6: seller delete ====");
		System.out.print("Enter id for delete test: ");
		int id = scan.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
		
		scan.close();

	}
}
