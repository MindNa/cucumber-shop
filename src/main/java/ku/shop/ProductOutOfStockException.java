package ku.shop;

public class ProductOutOfStockException extends Exception {
    public ProductOutOfStockException(){ }
    public ProductOutOfStockException(String message){
        super(message);
    }
}
