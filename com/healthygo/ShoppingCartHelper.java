package healthygo2.vijayanitech.com.healthygo;

import android.content.res.Resources;

import java.util.List;
import java.util.Vector;
 
public class ShoppingCartHelper {

 public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
 private static List<Product> catalog;
 private static List<Product> cart;
  
 public static List<Product> getCatalog(Resources res){
  if(catalog == null) {
   catalog = new Vector<Product>();

//   catalog.add(new Product("Tea", res.getDrawable(R.drawable.pic11),"Price 15", 15));
//   catalog.add(new Product("Masala Tea", res.getDrawable(R.drawable.pic12),"Price 25", 25));
//   catalog.add(new Product("Hot Coffee", res.getDrawable(R.drawable.pic13),"Price 30", 30));
//   catalog.add(new Product("Capuccino", res.getDrawable(R.drawable.pic14),"Price 40", 40));
//   catalog.add(new Product("Espresso", res.getDrawable(R.drawable.pic15),"Price 30", 30));
//   catalog.add(new Product("Americano", res.getDrawable(R.drawable.pic16),"Price 60", 60));
//   catalog.add(new Product("Cafe Latte", res.getDrawable(R.drawable.pic17),"Price 70", 70));
//   catalog.add(new Product("Slush", res.getDrawable(R.drawable.pic18),"Price 25", 15));
//   catalog.add(new Product("Blue Curacao", res.getDrawable(R.drawable.pic19),"Price 35", 25));
//   catalog.add(new Product("Lemon Ice Tea", res.getDrawable(R.drawable.pic20),"Price 35", 30));
//   catalog.add(new Product("Blood Orange", res.getDrawable(R.drawable.pic21),"Price 35", 40));
//   catalog.add(new Product("Mojito", res.getDrawable(R.drawable.pic22),"Price 40", 30));
//   catalog.add(new Product("Green Apple", res.getDrawable(R.drawable.pic23),"Price 40", 60));
//   catalog.add(new Product("Cafe Frappez", res.getDrawable(R.drawable.pic24),"Price 50", 70));
//   catalog.add(new Product("Fresh Fruit Shake", res.getDrawable(R.drawable.pic25),"Price 70", 15));
//   catalog.add(new Product("Strawberry Shake", res.getDrawable(R.drawable.pic26),"Price 60", 25));
//   catalog.add(new Product("Choco Frappez", res.getDrawable(R.drawable.pic27),"Price 60", 40));
//   catalog.add(new Product("Veg Schezwan", res.getDrawable(R.drawable.pic28),"Half-40,Full-60", 30));
//   catalog.add(new Product("Pan Seared Marinated Fillet of Basa Fish", res.getDrawable(R.drawable.pic29),"Price 300", 60));
//   catalog.add(new Product("Hot n Sour Soup", res.getDrawable(R.drawable.pic30),"Veg-50,NonVeg 70", 70));
//   catalog.add(new Product("Lung Fung Soup", res.getDrawable(R.drawable.pic31),"Veg-50,NonVeg 70", 25));
//   catalog.add(new Product("Lemon Corriander Soup", res.getDrawable(R.drawable.pic32),"Veg-50,NonVeg 70", 30));
//   catalog.add(new Product("Sweet Corn Soup", res.getDrawable(R.drawable.pic33),"Veg-50,NonVeg 70", 40));
//   catalog.add(new Product("Veg Noodles", res.getDrawable(R.drawable.pic34),"Half-30,Full-50", 30));
//   catalog.add(new Product("Chicken Noodles", res.getDrawable(R.drawable.pic35),"Half-60,Full-100", 60));
//   catalog.add(new Product("Chicken Noodles Schezwan", res.getDrawable(R.drawable.pic36),"Half-60,Full-100", 70));
//   catalog.add(new Product("Veg Noodles in Garlic Sauce", res.getDrawable(R.drawable.pic37),"Half-40,Full-60", 15));
//   catalog.add(new Product("Chicken Noodles in Garlic Sauce", res.getDrawable(R.drawable.pic38),"Half-60,Full-100", 25));
//   catalog.add(new Product("Veg Manchurian", res.getDrawable(R.drawable.pic39),"Price 100", 40));
//   catalog.add(new Product("Chicken Manchurian", res.getDrawable(R.drawable.pic40),"Price 170", 30));
//   catalog.add(new Product("Veg Fried Rice", res.getDrawable(R.drawable.pic41),"Half-40,Full-80", 60));
//   catalog.add(new Product("Chicken Fried Rice Schezwan Style", res.getDrawable(R.drawable.pic42),"Half-80,Full-160", 70));
//   catalog.add(new Product("Dal Makhani", res.getDrawable(R.drawable.pic43),"Price 90", 30));
//   catalog.add(new Product("Dal Fry", res.getDrawable(R.drawable.pic44),"Price 90", 40));
//   catalog.add(new Product("Dal Razma", res.getDrawable(R.drawable.pic45),"Price 90", 30));
//   catalog.add(new Product("Dal Chholey", res.getDrawable(R.drawable.pic46),"Price 90", 60));
//   catalog.add(new Product("Paneer Tikka", res.getDrawable(R.drawable.pic47),"Price 150", 70));
//   catalog.add(new Product("Paneer Matar", res.getDrawable(R.drawable.pic48),"Price 150", 15));
//   catalog.add(new Product("Paneer Bhujia", res.getDrawable(R.drawable.pic49),"Price 150", 25));
//   catalog.add(new Product("Kadhai Paneer", res.getDrawable(R.drawable.pic50),"Price 150", 40));
//   catalog.add(new Product("Aloo Matar Toato", res.getDrawable(R.drawable.pic51),"Price 100", 30));
//   catalog.add(new Product("Aloo zeera aloo", res.getDrawable(R.drawable.pic52),"Price 70", 60));
//   catalog.add(new Product("Aloo Mix Vegetables", res.getDrawable(R.drawable.pic53),"Price 80", 70));
//   catalog.add(new Product("Tandoori Roti", res.getDrawable(R.drawable.pic54),"Price 10", 60));
//   catalog.add(new Product("Butter Roti", res.getDrawable(R.drawable.pic55),"Price 10", 70));
//   catalog.add(new Product("Tawa Roti", res.getDrawable(R.drawable.pic56),"Price 10", 30));
//   catalog.add(new Product("Cheese Garlic Naan", res.getDrawable(R.drawable.pic57),"Price 35", 40));
//   catalog.add(new Product("Egg Curry", res.getDrawable(R.drawable.pic58),"Price 60", 30));
//   catalog.add(new Product("Chicken Curry", res.getDrawable(R.drawable.pic59),"Price 120", 60));
//   catalog.add(new Product("Butter Chicken(Boneless/Bone)", res.getDrawable(R.drawable.pic60),"Price 150", 70));
//   catalog.add(new Product("Kadhai Chicken", res.getDrawable(R.drawable.pic61),"Price 150", 15));
//   catalog.add(new Product("Chicken Spring Rolls", res.getDrawable(R.drawable.pic62),"Price 50", 25));
//   catalog.add(new Product("Crispy Chicken Sausages", res.getDrawable(R.drawable.pic63),"Price 100", 40));
//   catalog.add(new Product("Crispy Honey Chilli Chicken", res.getDrawable(R.drawable.pic64),"Price 150", 30));
//   catalog.add(new Product("Assorted Chicken Basket", res.getDrawable(R.drawable.pic65),"Price 225", 60));
//   catalog.add(new Product("Boiled Eggs", res.getDrawable(R.drawable.pic66),"Price 30", 70));
//   catalog.add(new Product("Plain Omlette", res.getDrawable(R.drawable.pic67),"Price 30", 30));
//   catalog.add(new Product("Masala Omlette", res.getDrawable(R.drawable.pic68),"Price 40", 60));
//   catalog.add(new Product("Bun Maska Omlette", res.getDrawable(R.drawable.pic69),"Price 50", 70));
//   catalog.add(new Product("Cheese Omlette", res.getDrawable(R.drawable.pic70),"Price 50", 60));
//   catalog.add(new Product("Egg Bhujia with two slices", res.getDrawable(R.drawable.pic71),"Price 50", 70));
//   catalog.add(new Product("Omlette with two slices", res.getDrawable(R.drawable.pic72),"Price 50", 30));
//   catalog.add(new Product("Omlette with Paratha", res.getDrawable(R.drawable.pic73),"Price 50", 40));
//   catalog.add(new Product("Fish n Chips", res.getDrawable(R.drawable.pic74),"Price 150", 30));
//   catalog.add(new Product("Veg Thali", res.getDrawable(R.drawable.pic75),"Price 70", 60));
//   catalog.add(new Product("NonVegCombo", res.getDrawable(R.drawable.pic76),"Price 90", 70));
//   catalog.add(new Product("Special Thali Veg", res.getDrawable(R.drawable.pic77),"Price 180", 15));
//   catalog.add(new Product("Special Thali NonVeg", res.getDrawable(R.drawable.pic78),"Price 220", 25));
//   catalog.add(new Product("Special RB Combo", res.getDrawable(R.drawable.pic79),"Price 200", 40));
//   catalog.add(new Product("RB Packers Attraction", res.getDrawable(R.drawable.pic80),"Price 375", 30));
//   catalog.add(new Product("Margherita Pizza", res.getDrawable(R.drawable.pic99),"Price 40", 60));
//   catalog.add(new Product("Vegetable Pizza", res.getDrawable(R.drawable.pic100),"Price 50", 70));
//   catalog.add(new Product("ChickenPizza", res.getDrawable(R.drawable.pic101),"Price 60", 60));
//   catalog.add(new Product("Classic Margherita Pizza", res.getDrawable(R.drawable.pic102),"Price 100-150", 70));
//   catalog.add(new Product("Corn Pizza", res.getDrawable(R.drawable.pic103),"Price 120-170", 15));
//   catalog.add(new Product("RB Special Garden Pizza", res.getDrawable(R.drawable.pic104),"Price 130-180", 25));
//   catalog.add(new Product("Farm House Pizza", res.getDrawable(R.drawable.pic105),"Price 150-200", 40));
//   catalog.add(new Product("PaneerTikka Pizza", res.getDrawable(R.drawable.pic106),"Price 160-220", 30));
//   catalog.add(new Product("Chicken Cheese Pizza", res.getDrawable(R.drawable.pic107),"Price 130-180", 60));
//   catalog.add(new Product("Chicken Corn Pizza", res.getDrawable(R.drawable.pic108),"Price 150-200", 70));
//   catalog.add(new Product("Chicken Tikka Pizza", res.getDrawable(R.drawable.pic109),"Price 170-220", 30));
//   catalog.add(new Product("Extra Toppings", res.getDrawable(R.drawable.pic110),"Price 30", 60));
//   catalog.add(new Product("BurgerWith Fries and Coke", res.getDrawable(R.drawable.pic111),"Price 80-100", 70));
//   catalog.add(new Product("Pizza 5inch Combination of 4", res.getDrawable(R.drawable.pic112),"Price 175-200", 60));
//   catalog.add(new Product("Pizza R combination of two", res.getDrawable(R.drawable.pic113),"Price 225-270", 70));
//   catalog.add(new Product("Pizza L combination of two", res.getDrawable(R.drawable.pic114),"Price 300-350", 30));
//   catalog.add(new Product("Cheesy Garlic Toast", res.getDrawable(R.drawable.pic115),"Price 80", 40));
//   catalog.add(new Product("Veg Cheese Pasta in Red Sauce", res.getDrawable(R.drawable.pic116),"Price 80", 30));
//   catalog.add(new Product("Veg Cheese Pasta in White Sauce", res.getDrawable(R.drawable.pic117),"Price 100", 60));
//   catalog.add(new Product("Chicken Cheese Pasta in Red Sauce", res.getDrawable(R.drawable.pic118),"Price 100", 70));
//   catalog.add(new Product("Chicken Cheese Pasta in white Sauce", res.getDrawable(R.drawable.pic119),"Price 120", 15));
//   catalog.add(new Product("Veg Burger", res.getDrawable(R.drawable.pic89),"Price 50", 60));
//   catalog.add(new Product("Twin Potato Burger", res.getDrawable(R.drawable.pic90),"Price 50", 70));
//   catalog.add(new Product("Sunny Side Up Egg Burger", res.getDrawable(R.drawable.pic91),"Price 60", 60));
//   catalog.add(new Product("Chicken Burger", res.getDrawable(R.drawable.pic92),"Price 70", 70));
//   catalog.add(new Product("Add French fries with Burger", res.getDrawable(R.drawable.pic93),"Price 30", 15));
//   catalog.add(new Product("Add Extra Cheese", res.getDrawable(R.drawable.pic94),"Price 15", 25));
//   catalog.add(new Product("Veg Grilled Sandwich", res.getDrawable(R.drawable.pic95),"Price 50", 40));
//   catalog.add(new Product("Veg Cheese Grilled Sandwich", res.getDrawable(R.drawable.pic96),"Price 70", 30));
//   catalog.add(new Product("Veg Club Sandwich With Fries", res.getDrawable(R.drawable.pic97),"Price 125", 60));
//   catalog.add(new Product("Chicken Grilled Sandwich", res.getDrawable(R.drawable.pic98),"Price 70", 70));
//   catalog.add(new Product("Truffle Cake Slice With Vanilla Scoop & Chocolate Sauce", res.getDrawable(R.drawable.pic81),"Price 80", 30));
//   catalog.add(new Product("Brownie With Hot Chocolate Sauce & Vanilla IceCream", res.getDrawable(R.drawable.pic82),"Price 90", 60));
//   catalog.add(new Product("Death By Chocolate", res.getDrawable(R.drawable.pic83),"Price 140", 70));
//   catalog.add(new Product("PanCakes With maple syrup/Chocolate Sauce/Fruit crush of your Choice", res.getDrawable(R.drawable.pic84),"Price 70", 60));
//   catalog.add(new Product("Veg Kathi Rollz", res.getDrawable(R.drawable.pic85),"Price 60", 70));
//   catalog.add(new Product("Paneer Kathi Rollz", res.getDrawable(R.drawable.pic86),"Price 70", 30));
//   catalog.add(new Product("Chicken Kathi Rollz", res.getDrawable(R.drawable.pic87),"Price 80", 40));
//   catalog.add(new Product("Paneer Tikka Paratha Rollz", res.getDrawable(R.drawable.pic88),"Price 50", 30));
     
  }

   
  return catalog;
 }
  
 public static List<Product> getCart() {
  if(cart == null) {
   cart = new Vector<Product>();
   cart.toArray();
  
   
   
 }
  return cart;
}

}