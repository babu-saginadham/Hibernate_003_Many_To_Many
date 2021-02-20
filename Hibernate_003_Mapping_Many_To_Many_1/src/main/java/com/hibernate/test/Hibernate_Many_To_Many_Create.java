package com.hibernate.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.entity.Degree;
import com.entity.Employee;

public class Hibernate_Many_To_Many_Create {
 
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
 
            
            Degree mba = new Degree();
            mba.setDegree_name("MBA");
            
            Degree be = new Degree();
            be.setDegree_name("BE");
            
            Set<Degree> degrees = new HashSet<Degree>();
            degrees.add(be);
            degrees.add(mba);
            
            Employee emp1 = new Employee();
            emp1.setName("Babu");
            emp1.setDegrees(degrees);
            
			session.save(emp1);
			
			//emp2
			Employee emp2 = new Employee();
            emp2.setName("XYZ");
            Set<Degree> degrees2 = new HashSet<Degree>();
            degrees2.add(be);
            emp2.setDegrees(degrees2);
            
			session.save(emp2);
            
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
