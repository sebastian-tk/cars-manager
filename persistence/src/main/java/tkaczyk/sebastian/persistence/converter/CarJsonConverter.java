package tkaczyk.sebastian.persistence.converter;

import tkaczyk.sebastian.persistence.Car;

public class CarJsonConverter extends JsonConverter<Car>{
    public CarJsonConverter(String jsonFileName) {
        super(jsonFileName);
    }
}
