package training.iqgateway;

import javax.jws.WebService;

@WebService
public class CurrencyConvertor {
    
    public double dollarsToRs(double dollars){
        return 69.23 * dollars;   
    }
    
    public double poundsToRs(double pounds){
        return 81.45 * pounds;   
    }
    
    public double riyalsToRs(double riyals){
        return 17.67 * riyals;   
    }
    
    public double yensToRs(double yens){
        return 0.5 * yens;   
    }
}
