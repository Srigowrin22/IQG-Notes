package training.iqgateway.operations;

import training.iqgateway.entities.AdminEO;

public interface Operations<T> {

    public boolean login(T user);

    public boolean logout(T user);
    
}