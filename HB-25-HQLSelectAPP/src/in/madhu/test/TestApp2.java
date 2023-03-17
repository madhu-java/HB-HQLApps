package in.madhu.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.madhu.model.Product;
import in.madhu.util.HibernateUtil;

public class TestApp2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Product> query = session.createQuery("FROM in.ineuron.model.Product WHERE pname IN(:prod1,:prod2)");

			//Set values to Named Parameter
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "tissot");
			
			// Execute the query(select * from product where pname in ("fossil","tissot"))
			List<Product> products = query.list();

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
