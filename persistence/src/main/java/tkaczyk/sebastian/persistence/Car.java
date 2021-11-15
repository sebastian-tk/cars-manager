package tkaczyk.sebastian.persistence;

import lombok.*;

import java.math.BigDecimal;

@Builder
@EqualsAndHashCode
public class Car {
    String model;
    BigDecimal price;
    int mileage;
    Engine engine;
    CarBody carBody;
    Wheel wheel;
    /**
     *
     * @param startRange object BigDecimal
     * @param finishRange object BigDecimal
     * @return  true, if price is in range between startRange and finishRange
     */
    public boolean isPriceInRange(BigDecimal startRange, BigDecimal finishRange){
        return price.compareTo(startRange)>=0 && price.compareTo(finishRange)<=0;
    }
}
