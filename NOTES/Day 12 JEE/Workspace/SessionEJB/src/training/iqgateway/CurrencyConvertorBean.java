package training.iqgateway;

import javax.ejb.Stateless;

@Stateless(name = "CurrencyConvertor", mappedName = "EJBApp-SessionEJB-CurrencyConvertor")
public class CurrencyConvertorBean implements CurrencyConvertor,
                                              CurrencyConvertorLocal {
    public CurrencyConvertorBean() {
    }

    public double diharamsToRs(double diharam) {
        return 30.45;
    }

    public double dollarsToRs(double dollars) {
        return 69.56 * dollars;
    }

    public double eurosToRs(double euros) {
        return 71.67 * euros;
    }

    public double pesosToRupees(double pesos) {
        return 0.378 * pesos;
    }

    public double poundsToRs(double pounds) {
        return 80.23 * pounds;
    }

    public double riyalsToRupees(double riyals) {
        return 17.23 * riyals;
    }

    public double yensToRupees(double yens) {
        return 0.5 * yens;
    }
}
