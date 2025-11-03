package training.iqgateway.model;

import javax.ejb.Stateless;

@Stateless(name = "CurrencyConvertor", mappedName = "Lesson17-JEEApps-Model-CurrencyConvertor")
public class CurrencyConvertorBean implements CurrencyConvertor,
                                              CurrencyConvertorLocal {
    public CurrencyConvertorBean() {
        
    }
    
    
}
