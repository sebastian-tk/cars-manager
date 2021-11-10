package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tkaczyk.sebastian.persistence.type.EngineType;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.extensions.CarServiceExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tkaczyk.sebastian.persistence.type.EngineType.*;
import static tkaczyk.sebastian.service.extensions.CarFactory.*;

@ExtendWith(CarServiceExtension.class)
@RequiredArgsConstructor
public class CarServiceWithSortedCarsByNameWithEngineTypeTest {
    private final CarsService carsService;

    @Test
    @DisplayName("should throw CarServiceException when engineType is null")
    public void test1(){
        assertThatThrownBy(()->carsService.withSortedCarsByNameWithEngineType(null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Argument engineType is null");
    }

    @ParameterizedTest
    @DisplayName("should return sorted cars by name with required engineType")
    @EnumSource(EngineType.class)
    public void test2(EngineType engineType){
        var expectedCars = Map.of(
                DIESEL,List.of(bmw,ford,toyota),
                GASOLINE,List.of(fiat,honda,mazda,mercedes,renault),
                LPG,List.of(opel)
        );
        assertThat(carsService.withSortedCarsByNameWithEngineType(engineType))
                .containsExactlyElementsOf(expectedCars.get(engineType));
    }

}
