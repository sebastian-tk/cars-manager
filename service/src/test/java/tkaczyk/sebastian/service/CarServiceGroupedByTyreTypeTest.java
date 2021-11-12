package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.type.TyreType;
import tkaczyk.sebastian.service.extensions.CarServiceExtension;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static tkaczyk.sebastian.persistence.type.TyreType.SUMMER;
import static tkaczyk.sebastian.persistence.type.TyreType.WINTER;
import static tkaczyk.sebastian.service.extensions.CarFactory.*;

@ExtendWith(CarServiceExtension.class)
@RequiredArgsConstructor
public class CarServiceGroupedByTyreTypeTest {
    private final CarsService carService;

    @Test
    @DisplayName("should return map with TyreType as key and Cars as values sorted descending")
    public void test1() {
        Map<TyreType, List<Car>> expectedCars = new LinkedHashMap<>();
        expectedCars.put(WINTER, List.of(bmw, ford, honda,opel, renault, toyota));
        expectedCars.put(SUMMER, List.of(fiat, mazda, mercedes));

        assertThat(carService.groupByTyreType())
                .containsExactlyEntriesOf(expectedCars);

    }
}
