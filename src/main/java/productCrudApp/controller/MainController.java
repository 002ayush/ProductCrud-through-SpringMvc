package productCrudApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productCrudApp.dao.ProductDao;
import productCrudApp.model.Product;

@Controller
public class MainController {
	@Autowired
	private ProductDao productdao;
	@RequestMapping("/")
	public String home(Model m) {
		System.out.println("This is the home page");
		List<Product> allProducts = productdao.getAllProducts();
		m.addAttribute("products", allProducts);
		return "index";
	}
	@RequestMapping("/add_product")
	public String addProduct(Model m) {
		m.addAttribute("title","Add Product App");
		return "add_product_form";
	}
	@RequestMapping(value = "/handle-product",method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest request) {
		System.out.println(product);
		this.productdao.createProduct(product);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	@RequestMapping("/delete/{product_id}")
	public RedirectView delete(@PathVariable("product_id") int pid, HttpServletRequest request) {
		this.productdao.deleteProduct(pid);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	@RequestMapping("/update/{prod_id}")
	public String updateform(@PathVariable("prod_id") int pid,Model model) {
		Product product = this.productdao.getProduct(pid);
		model.addAttribute("product", product);
		return "update_form";
	}
}
