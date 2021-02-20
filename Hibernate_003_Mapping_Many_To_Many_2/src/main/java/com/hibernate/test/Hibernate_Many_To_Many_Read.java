package com.hibernate.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.entity.Degree;
import com.entity.Employee;

public class Hibernate_Many_To_Many_Read {

	public static void main(String[] args) {

		SessionFactory sessionFactoryObj = null;
		Session session = null;

		try {

			// Creating Configuration Instance & Passing Hibernate Configuration File
			Configuration configObj = new Configuration();
			configObj.configure("hibernate.cfg.xml");

			configObj.addAnnotatedClass(com.entity.Employee.class);
			configObj.addAnnotatedClass(com.entity.Degree.class);


			// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
			ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

			// Creating Hibernate SessionFactory Instance
			sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);

			session = sessionFactoryObj.openSession();
			session.beginTransaction();


			Query u = session.createQuery("from Employee");
			List<Employee> employees = u.getResultList();
			System.out.println("Employees List:" + employees.size());
			for(Employee e: employees) {
				System.out.println(e.getName() + "Employees Degrees:" + e.getDegrees().size());
			}
			
			System.out.println("\n");
			
			//Degree
			Query q2 = session.createQuery("from Degree");
			List<Degree> degrees = q2.getResultList();
			System.out.println("Degree List:" + degrees.size());
			for(Degree d:degrees) {
				System.out.println(d.getDegree_name() + " holding by no.of employees" + d.getEmployees().size());
			}


			// Committing The Transactions To The Database
			session.getTransaction().commit();
		} catch(Exception sqlException) {
			System.out.println("Exception :" + sqlException);

			if(null != session && null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}

		System.exit(0);
	}

}
