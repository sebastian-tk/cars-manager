package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.CarUtils;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.extensions.CarServiceExtension;
import tkaczyk.sebastian.service.type.SortBy;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CarServiceExtension.class)
@RequiredArgsConstructor
public class CarServiceSortByTest {
    private final CarsService carsService;

    @Test
    @DisplayName("should throw CarServiceException when sortBy is null")
    public void test1(){
        assertThatThrownBy(()->carsService.sortBy(null,true))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Argument sortBy is null");
    }

    @ParameterizedTest
    @DisplayName("should return Cars sorted by sortBy instance ascending")
    @EnumSource(SortBy.class)
    public void test2(SortBy sortBy){
        var expectedCars = Map.of(
                SortBy.AMOUNT_COMPONENTS, List.of("FIAT","TOYOTA","MAZDA","OPEL","BMW","MERCEDES"),
                SortBy.POWER_ENGINE, List.of("OPEL","FIAT","MAZDA","TOYOTA","MERCEDES","BMW"),
                SortBy.SIZE_WHEEL, List.of("FIAT","OPEL","TOYOTA","MERCEDES","BMW","MAZDA")
        );
        assertThat(carsService
                        .sortBy(sortBy, false)
                        .stream()
                        .map(CarUtils.toModel)
                        .toList()
        ).containsExactlyElementsOf(expectedCars.get(sortBy));
    }

    @ParameterizedTest
    @DisplayName("should return Cars sorted by sortBy instance descending")
    @EnumSource(SortBy.class)
    public void test3(SortBy sortBy){
        var expectedCars = Map.of(
                SortBy.AMOUNT_COMPONENTS,List.of("MERCEDES","BMW","OPEL","MAZDA","TOYOTA","FIAT"),
                SortBy.POWER_ENGINE, List.of("BMW","MERCEDES","TOYOTA","MAZDA","FIAT","OPEL"),
                SortBy.SIZE_WHEEL, List.of("MAZDA","BMW","MERCEDES","TOYOTA","OPEL","FIAT")
        );
        assertThat(carsService
                .sortBy(sortBy, true)
                .stream()
                .map(CarUtils.toModel)
                .toList()
        ).containsExactlyElementsOf(expectedCars.get(sortBy));
    }
}
