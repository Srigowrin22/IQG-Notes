/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lesson03.behavioural.dp.chainofresp;

/**
 *
 * @author srigowri.n
 */
public class Rupee200Dispenser implements DispenseChain{
    private DispenseChain chain;
    
    @Override
    public void setNextChain(DispenseChain nextChain){
        this.chain = nextChain;
    }
    
    @Override
    public void dispense(Currency cur){
        if(cur.getAmount() >= 200){
            int num = cur.getAmount()/200;
            int remainder = cur.getAmount()%200;
            System.out.println("Dispensing " + num + " 200 Notes");
            if(remainder!=0){
                this.chain.dispense(new Currency(remainder));
            }
        }else{
            this.chain.dispense(cur);
        }
    }
}
