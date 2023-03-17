package in.madhu.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.madhu.util.HibernateUtil;

public class TestApp4 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Object[]> query = session.createQuery("SELECT pname,price,qty FROM in.ineuron.model.Product WHERE pname IN(:prod1,:prod2)");

			//Set values to Named Parameter
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "tissot");
			
			// Execute the query(select price,qty,pname from product where pname in ("fossil","tissot"))
			List<Object[]> products = query.list();
			
			System.out.println("PNAME\tPRICE\tQTY");
			
			// process the List Object
			products.forEach(row->{
				for (Object object : row) {
					System.out.print(object+"\t");
				}
				System.out.println();
			});

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
