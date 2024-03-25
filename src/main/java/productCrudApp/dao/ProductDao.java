package productCrudApp.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import productCrudApp.model.Product;

@Component		
public class ProductDao {
	
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//This method is used for create the product
	@Transactional
	public void createProduct(Product product) {
		this.hibernateTemplate.saveOrUpdate(product);
	}
	//To display all the products
	public List<Product> getAllProducts(){
		List<Product> allProduct = this.hibernateTemplate.loadAll(Product.class);
		return allProduct;
	}
	//To delete the single product
	@Transactional
	public void deleteProduct(int p_id) {
		Product product = this.hibernateTemplate.load(Product.class,p_id);
		this.hibernateTemplate.delete(product);
	}
	//To get the single product....
	public Product getProduct(int p_id) {
		Product product = this.hibernateTemplate.get(Product.class, p_id);
		return product;
	}
}
