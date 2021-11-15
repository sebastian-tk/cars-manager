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
        if(startRange == null || finishRange == null){
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        if(startRange.compareTo(BigDecimal.ZERO)<=0 || finishRange.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Arguments must be positive");
        }
        if(startRange.compareTo(finishRange) > 0){
            throw new IllegalArgumentException("Invalid range");
        }
        return price.compareTo(startRange)>=0 && price.compareTo(finishRange)<=0;
    }
}
