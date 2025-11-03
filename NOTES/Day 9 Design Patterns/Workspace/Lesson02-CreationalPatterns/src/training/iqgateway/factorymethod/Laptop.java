/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.factorymethod;

/**
 *
 * @author srigowri.n
 */

public class Laptop extends WorkStation{
    
    private String RAM;
    private String SSD;
    private String CPU;

    public Laptop(String RAM, String SSD, String CPU) {
        this.RAM = RAM;
        this.SSD = SSD;
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getSSD() {
        return SSD;
    }

    public String getCPU() {
        return CPU;
    }
}
