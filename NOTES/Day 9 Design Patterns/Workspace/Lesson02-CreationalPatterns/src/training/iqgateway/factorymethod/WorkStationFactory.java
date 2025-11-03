/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.factorymethod;

/**
 *
 * @author srigowri.n
 */
public class WorkStationFactory {
    // Factory is a place where products are created.
    // In Java context Factory method created required objects

    public static WorkStation getWorkStation(String givenType, String givenRAM, String givenSSD, String givenCPU) {
        if ("Laptop".equalsIgnoreCase(givenType)) {
            return new Laptop(givenRAM, givenSSD, givenCPU);
        } else if ("Server".equalsIgnoreCase(givenType)) {
            return new Server(givenRAM, givenSSD, givenCPU);
        } else if ("Desktop".equalsIgnoreCase(givenType)) {
            return new Desktop(givenRAM, givenSSD, givenCPU);

        }
        return null;
    }
}
