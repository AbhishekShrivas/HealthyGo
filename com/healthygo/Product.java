package healthygo2.vijayanitech.com.healthygo;

public class Product {

 public String title;
 public String productImage;
 public String description;
 public double price;
 public boolean selected;


 public Product(String title, String productImage, String description,double price) {
  this.title = title;
  this.productImage = productImage;
  this.description = description;
  this.price = price;
 }
 @Override
 public String toString(){
	 return title;
 }
}
