/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lesson03.behavioural.dp.chainofresp;

/**
 *
 * @author srigowri.n
 */
public class Rupee100Dispenser implements DispenseChain{
    private DispenseChain chain;
    
    @Override
    public void setNextChain(DispenseChain nextChain){
        this.chain = nextChain;
    }
    
    @Override
    public void dispense(Currency cur){
        if(cur.getAmount() >= 100){
            int num = cur.getAmount()/100;
            int remainder = cur.getAmount()%100;
            System.out.println("Dispensing " + num + " 100 Notes");
            if(remainder!=0){
                this.chain.dispense(new Currency(remainder));
            }
        }else{
            this.chain.dispense(cur);
        }
    }
}
