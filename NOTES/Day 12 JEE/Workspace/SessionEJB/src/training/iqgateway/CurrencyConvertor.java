package training.iqgateway;

import javax.ejb.Remote;

@Remote
public interface CurrencyConvertor {

    public double dollarsToRs(double dollars);

    public double poundsToRs(double pounds);

    public double eurosToRs(double euros);

    public double diharamsToRs(double diharam);

    public double yensToRupees(double yens);

    public double riyalsToRupees(double riyals);

    public double pesosToRupees(double pesos);
}
