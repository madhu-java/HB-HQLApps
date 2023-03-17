package in.madhu.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.madhu.util.HibernateUtil;

public class TestApp3 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Integer> query = session.createQuery("SELECT price FROM in.ineuron.model.Product WHERE pname IN(:prod1,:prod2)");

			//Set values to Named Parameter
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "tissot");
			
			// Execute the query(select price,qty,pname from product where pname in ("fossil","tissot"))
			List<Integer> products = query.getResultList();
			
			System.out.println("PRICE");
			
			// process the List Object
			products.forEach(System.out::println);
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
