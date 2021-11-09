package tkaczyk.sebastian.persistence;

import lombok.*;
import tkaczyk.sebastian.persistence.type.CarBodyType;

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
     * @param typeInput object CarBodyType
     * @return  true, if carBodyType from carBody is equal with typeInput
     */
    public boolean isCarBodyTypeEqual(CarBodyType typeInput){
        return this.carBody.type.equals(typeInput);
    }

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
