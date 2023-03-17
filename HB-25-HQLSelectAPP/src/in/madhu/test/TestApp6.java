package in.madhu.test;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.madhu.model.Product;
import in.madhu.util.HibernateUtil;

public class TestApp6 {

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Product> query = session.createQuery("FROM in.ineuron.model.Product WHERE pid=:id");

			System.out.print("Enter the id of the product to be fetched :: ");
			int id = new Scanner(System.in).nextInt();
			
			
			// Set values to Named Parameter
			query.setParameter("id", id);

			// Execute the query(select * from product where pid = ?)
			Product product = query.uniqueResult();
			if (product != null) {
				System.out.println(product);
			}else {
				System.out.println("Record not available for the given id :: "+id);
			}
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
