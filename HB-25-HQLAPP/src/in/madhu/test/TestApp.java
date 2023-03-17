package in.madhu.test;

import java.util.Optional;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.madhu.model.Product;
import in.madhu.util.HibernateUtil;

public class TestApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			/*getting all of the object
			Query<Product> query = session.createQuery("FROM in.madhu.model.Product");// select * from product;
            
			List<Product> products = query.list();

			// process the List Object
			products.forEach(System.out::println);*/
			
			
			/*getting only few records
			Query <Product> query = session.createQuery("From in.madhu.model.Product where pname in(:prod1,:prod2)");
             query.setParameter("prod1","aa");
             query.setParameter("prod2", "bb");
			// Execute the query
			List<Product> products = query.list();

			// process the List Object
			products.forEach(System.out::println);*/
			
			
			
			/* getting only few columns
			Query<Object[]> query=session.createQuery("select pname,price,qty from in.madhu.model.Product where pname in(:prod1,:prod2)") ;
			query.setParameter("prod1","aa");
            query.setParameter("prod2", "bb");
            
            List<Object[]> products = query.list();
            System.out.println("pnmae\tprice\tqty");
            /*for(Object[] obj:products) {
            	System.out.println(obj[0]+","+obj[1]);
            }
            products.forEach(row->{
            	for(Object obj:row)
            		System.out.print(obj+"\t");
            	System.out.println();
            });
*/
			
			//getting single column
		/*	
			Query<Integer> query=session.createQuery("select price from in.madhu.model.Product where pname in(:prod1,:prod2)");
			query.setParameter("prod1","aa");
            query.setParameter("prod2", "bb");
			List<Integer> products = query.list();
			System.out.println("price");
			products.forEach(System.out::println);*/
			Query<Product> query = session.createQuery("From in.madhu.model.Product where pid=:pid");
			System.out.println("enter id:");
			int id= new Scanner(System.in).nextInt();
			
			query.setParameter("pid", id);
			/*List<Product> products = query.list();
			System.out.println(products.size());
			
			if(!products.isEmpty()) {
				System.out.println(products.get(0));
			}else
				System.out.println("record not availble with:"+id);*/
			
			/*Product product = query.uniqueResult();
			
			
			if(product!=null) {
				System.out.println(product);
				
			}else
				System.out.println("record not availble with:"+id);*/
			Optional<Product> optional = query.uniqueResultOptional();
			if(optional.isPresent()) {
System.out.println(optional.get());
				
			}else
				System.out.println("record not availble with:"+id);	
			
			
			
			
			
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
