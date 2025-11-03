package oe;

public class OrderItem {
    private int lineNbr;
    private int quantity = 1;
    private double unitPrice;
    private Product product;

    public OrderItem(Product newProduct) {
        product = newProduct;
        unitPrice = product.getRetailPrice();
    }

    public int getLineNbr() {
        return lineNbr;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getItemTotal() {
        return quantity * unitPrice;
    }

    public void setLineNbr(int newLineNbr) {
        lineNbr = newLineNbr;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    public void setUnitPrice(double newUnitPrice) {
        unitPrice = newUnitPrice;
    }

    public double getTax() {
        double itemTax = 0.0;
        if (product instanceof Taxable) {
            itemTax = ((Taxable)product).getTax(getItemTotal());
        }
        return itemTax;
    }

    public String toString() {
        if (product instanceof Taxable)
            return lineNbr + " " + product + " " + quantity + " Tax: " +
                Util.toMoney(getTax());
        else
            return lineNbr + " " + product + " " + quantity + " " +
                Util.toMoney(getItemTotal());
    }

    public void setProduct(Product newProduct) {
        product = newProduct;
    }

    public Product getProduct() {
        return product;
    }


}
