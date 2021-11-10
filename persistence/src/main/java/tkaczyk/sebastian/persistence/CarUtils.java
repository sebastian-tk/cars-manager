package tkaczyk.sebastian.persistence;

import tkaczyk.sebastian.persistence.type.EngineType;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Function;

public interface CarUtils {
    Function<Car,String> toModel = car -> car.model;

    Comparator<Car> sortBySizeWheel = Comparator.comparing(car -> car.wheel.size);
    Comparator<Car> sortByPowerEngine = Comparator.comparing(car -> car.engine.power);
    Comparator<Car> sortByAmountComponents = Comparator.comparing(car -> car.carBody.components.size());
    Comparator<Car> sortByModel = Comparator.comparing(car -> car.model);

    BiPredicate<Car, EngineType> carWithEngineType = (car,type) -> car.engine.type.equals(type);
}
