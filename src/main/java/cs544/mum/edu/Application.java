package cs544.mum.edu;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cs544.mum.edu.model.Administrator;
import cs544.mum.edu.model.Project;
import cs544.mum.edu.model.Resource;
import cs544.mum.edu.model.Status;
import cs544.mum.edu.model.Task;
import cs544.mum.edu.model.Volunteer;


public class Application {
	private static Session session;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration();
			
			// This step will read hibernate.cfg.xml
			sessionFactory = configuration.configure().buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println(ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {
		session = sessionFactory.openSession();
		createProject();
		session.close();
	}

	public static void assignedMoreTaskToUser() {
		
	}
	

	private static void createProject() {
		Transaction tx = session.beginTransaction();
		try {
			Resource resource = new Resource();
			resource.setDescription("Car");
			session.persist(resource);
			
			Resource resource2 = new Resource();
			resource2.setDescription("Bicycle");
			session.persist(resource2);
			
			Resource resource3 = new Resource();
			resource2.setDescription("Laptop");
			session.persist(resource3);
			
			Task task1 = new Task();
			task1.setStatus(Status.NEW);
			task1.setResources(Arrays.asList(resource, resource2, resource3));
			task1.setTimeFrame(10);
			task1.setDescription("Do survey");
			session.persist(task1);
			
			Volunteer v1 = new Volunteer();
			v1.setName("Jimmy");
			v1.addTask(task1);
			session.persist(v1);

			
			Project project = new Project();
			project.setDescription("Child Safety");
			project.setStatus(Status.INPROGRESS);
			project.setExpectedStartDate(sdf.parse("2016-09-10"));
			project.setExpectedEndDate(sdf.parse("2016-10-10"));
			project.setLocation("Farefield");
			project.addTask(task1);
			session.persist(project);
			
			Administrator admin1 = new Administrator();
			admin1.setName("Dara");
			admin1.addProject(project);
			session.persist(admin1);
			
			
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
		}
	}
}
