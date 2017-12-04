package test_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateExecute {
	public static void main(String[] args)
	{
		HibernateExecute execute = new HibernateExecute();
		execute.add();
	}
	
	public void add()
	{
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setName("test");
		user.setAge(0);
		user.setMobile("13012341234");
		user.setEmail("123123@qq.com");
		user.setPassword("123123");
		session.save(user);
		tx.commit();
		session.close();
		sf.close();
	}
}
