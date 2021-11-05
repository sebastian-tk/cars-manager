package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import tkaczyk.sebastian.persistence.Car;

import java.util.Set;

@RequiredArgsConstructor
public class CarsService {
    private Set<Car> cars;

}
