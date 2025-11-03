package training.iqgateway.programs;

public class Smartphone {
    private String brand;
    private String model;
    private int storageCapacity;

    public Smartphone(String brand, String model, int storageCapacity) {
        this.brand = brand;
        this.model = model;
        this.storageCapacity = storageCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public void increaseStorage(int amount) {
        this.storageCapacity += amount;
    }

    public static void main(String[] args) {
        Smartphone myPhone = new Smartphone("Apple", "iPhone 15", 256);

        System.out.println("Brand: " + myPhone.getBrand());
        System.out.println("Model: " + myPhone.getModel());
        System.out.println("Storage Capacity: " +
                           myPhone.getStorageCapacity() + " GB");

        myPhone.increaseStorage(128);
        System.out.println("Updated Storage Capacity: " +
                           myPhone.getStorageCapacity() + " GB");

        myPhone.setBrand("Samsung");
        myPhone.setModel("Galaxy S24");
        myPhone.setStorageCapacity(512);

        System.out.println("Brand: " + myPhone.getBrand());
        System.out.println("Model: " + myPhone.getModel());
        System.out.println("Storage Capacity: " +
                           myPhone.getStorageCapacity() + " GB");
    }
}

