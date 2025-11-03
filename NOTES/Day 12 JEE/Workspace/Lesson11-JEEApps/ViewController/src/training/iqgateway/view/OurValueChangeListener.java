package training.iqgateway.view;

import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class OurValueChangeListener implements ValueChangeListener{
    
    public void processValueChange(ValueChangeEvent valueChangeEvent){
        System.out.println("Value change listerner is listening!,.....");
    }
}
