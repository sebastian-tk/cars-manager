package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tkaczyk.sebastian.service.extensions.CarServiceExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static tkaczyk.sebastian.service.extensions.CarFactory.*;

@ExtendWith(CarServiceExtension.class)
@RequiredArgsConstructor
public class CarServiceGroupByCarWithMileageTest {
    private final CarsService carsService;


    @Test
    @DisplayName("should returns a map with cars as a keys and an appropriately mileages values")
    public void test1() {
        var expectedCars = Map.of(
                opel, 1500,
                mazda, 1300,
                mercedes, 1300,
                bmw, 1000,
                honda, 1000,
                ford, 700,
                fiat, 300,
                renault, 200,
                toyota, 200
        );
        assertThat(carsService.groupByCarWithMileage()).containsExactlyInAnyOrderEntriesOf(expectedCars);
    }
    @Test
    @DisplayName("should returns a map with values in descending order")
    public void test2() {
        var expectedOrderValues = List.of( 1500, 1300, 1300, 1000, 1000, 700, 300, 200, 200);

        assertThat(carsService.groupByCarWithMileage().values()).containsExactlyElementsOf(expectedOrderValues);
    }
}





















