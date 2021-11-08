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
}
