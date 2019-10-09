package ku.shop;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;

    @Before
    public void setup() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("the following products exist:")
    public void the_following_products_exist(DataTable table){
        Map<String,Double> data = table.asMap(String.class, Double.class);

        for (String name : data.keySet()){
            double price = data.get(name);
            catalog.addProduct(name, price);
        }
    }

    @Given("a product (.+) with price (.+) exists")
    public void a_product_with_price_exists(String name, double price) {
        catalog.addProduct(name, price);
    }

    @Given("products (.+) with price (.+) with quantity (.+) in stock")
    public void products_in_stock(String name, double price, int quantity){
        catalog.addProduct(name,price, quantity);
    }

    @When("I buy (.+) with quantity (.+)")
    public void i_buy_with_quantity(String name, int quant) {
            Product prod = catalog.getProduct(name);
            order.addItem(prod, quant);
    }

    @When("I want to check quantity of (.+)")
    public void i_want_to_check_quantity_of(String name){
        catalog.checkQuantityOfProduct(name);
    }

    @Then("total should be (.+)")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Then("quantity of product (.+) should be (.+)")
    public void quantity_of_product_should_be(String name, int quant){
        assertEquals(quant, catalog.checkQuantityOfProduct(name));
    }

    @Then("a product (.+) with quantity (.+) should out of stock")
    public void a_product_should_out_of_stock(String name, int quant) throws ProductOutOfStockException{
        assertThrows(ProductOutOfStockException.class, () -> catalog.buyProducts(name,quant));
    }

}
