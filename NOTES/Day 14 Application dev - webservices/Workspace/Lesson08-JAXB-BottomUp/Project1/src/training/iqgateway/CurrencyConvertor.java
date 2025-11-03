package training.iqgateway;

import javax.jws.WebService;

@WebService
public class CurrencyConvertor {
    public Double dollarToRs(double dollar) {
        return 69.23 * dollar;
    }
    
    public Double eurosToRs(double euros) {
        return 78.12 * euros;
    }
    
    public Double yensToRs(double yens) {
        return 0.5 * yens;
    }
}
