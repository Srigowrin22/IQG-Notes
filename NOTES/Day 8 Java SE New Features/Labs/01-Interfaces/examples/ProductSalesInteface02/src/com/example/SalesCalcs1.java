package com.example;

public interface SalesCalcs1 {
  public static final String reportTitle="\n==Static List Report==";
  
  public String getName();
  public double calcSalesPrice();
  public double calcCost();
  public double calcProfit();
  
  public static void printItemArray(SalesCalcs1[] items){
    System.out.println(reportTitle);
      for(SalesCalcs1 item:items){
        System.out.println("--" + item.getName() + " Report--");
        System.out.println("Sales Price: " + item.calcSalesPrice());
        System.out.println("Cost: " + item.calcCost());
        System.out.println("Profit: " + item.calcProfit());
      }
  }
  
  public default void printItemReport(){
    System.out.println("--" + this.getName() + " Report--");
    System.out.println("Sales Price: " + this.calcSalesPrice());
    System.out.println("Cost: " + this.calcCost());
    System.out.println("Profit: " + this.calcProfit());    
  }
}
