package hello;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String productName;
	private int unitPrice;
	private int amountInStock;
	
	/*@JoinTable(name = "orders_items",  
				joinColumns = @JoinColumn(name = "product_name"),inverseJoinColumns = @JoinColumn(name = "total_price"))*/
	@ManyToMany(targetEntity=Orders.class, mappedBy="items")
	private List<Orders> orders;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getAmountInStock() {
		return amountInStock;
	}
	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
}
