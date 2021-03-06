package statge2.ecommerce.onlinemarketbackend.dao.imp;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import statge2.ecommerce.onlinemarketbackend.dao.ProductDAO;
import statge2.ecommerce.onlinemarketbackend.dto.Product;
@Transactional
@Repository("productDAO")
public class ProductDAOImp implements ProductDAO {
	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	public boolean addProduct(Product product) {
		try{
			sessionFactory.getCurrentSession().persist(product);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
		
	}

	@Override
	public List<Product> allProducts() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("From Product").getResultList();
	}

	@Override
	public Product get(int id) 
	{
	     
		return sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@Override
	public boolean update(Product product) {
		System.out.println("Product Price::::::::::::::::::::"+product.getProductPrice());
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().update(product);
			/*Query q=sessionFactory.getCurrentSession().createQuery("update Product set productDespription=:pdesc,productName=:pname,productPrice=:pprice,quantity=:pqan,status=:status where productid=:id");
			q.setParameter("pdesc",product.getProductDespription());
			q.setParameter("pname", product.getProductName());
			q.setParameter("pprice", product.getProductPrice());
			q.setParameter("pqan",product.getQuantity());
			q.setParameter("status", product.isStatus());
			q.setParameter("id", product.getProductId());
		
			q.executeUpdate();*/
			return true;
		}catch(Exception e){
			System.out.println("exception in ProductDAO:::::::::::::::"+e);
		return false;
		}
	}

	@Override
	public boolean delete(int id) {
		System.out.println("testing");
		try{
		 Query q= sessionFactory.getCurrentSession().createQuery("DELETE Product WHERE productId = :ID");
		 q.setParameter("ID", id);
		 q.executeUpdate();
		 return true;
	
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	
	}
	
}
