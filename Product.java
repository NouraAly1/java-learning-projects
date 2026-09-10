
class Product{
    //===============Attributes==============
    String name;
    double price;
    String category;

   //==============constructor==============
    Product(String name, double price, String category){
        this.name = name;
        this.price = price;
        this.category = category;

    }

    //==============Methods=================
    void printInfo(){
        System.out.println("Product: " + name);
        System.out.println("Price: " + price);
        System.out.println("Category: " + category);


}

    void applyDiscount(double percent){
        double amount = price * (percent / 100);
        price = price - amount;
        System.out.println("Boots price after " + percent + " % discount: " + price);

}


public class OOP{

    public static void main (String[] args){

        Product candy = new Product( "Gum ", 5, "Food");
        Product shirts = new Product( "shirt ", 50, "Clothes");
        Product boots = new Product( "boot ", 100, "Shoes");

    
    candy.printInfo();
    shirts.printInfo();
    boots.printInfo();
    boots.applyDiscount(20);
    boots.printInfo();
 }
}

}

