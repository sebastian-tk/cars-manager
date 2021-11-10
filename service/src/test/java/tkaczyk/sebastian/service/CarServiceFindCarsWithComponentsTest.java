package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.extensions.CarServiceExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tkaczyk.sebastian.service.extensions.CarFactory.*;

@ExtendWith(CarServiceExtension.class)
@RequiredArgsConstructor
public class CarServiceFindCarsWithComponentsTest {
    private final CarsService carsService;

    @Test
    @DisplayName("should throw CarServiceException when argument components is null")
    public void test1(){
        assertThatThrownBy(()->carsService.findCarsWithComponents(null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Invalid component list argument");
    }
    @Test
    @DisplayName("should throw CarServiceException when argument components is empty")
    public void test2(){
        assertThatThrownBy(()->carsService.findCarsWithComponents(List.of()))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Invalid component list argument");
    }
    @ParameterizedTest
    @DisplayName("should return sorted cars by model which contain require components")
    @MethodSource("componentsFactory")
    public void test3(int idx,List<String> components){
        var expectedCars = List.of(
                List.of(bmw,renault),
                List.of(bmw,ford,mercedes),
                List.of(bmw,ford,mazda,mercedes),
                List.of(bmw,fiat,ford,honda,mercedes,renault),
                List.of(bmw,ford,honda,mercedes,renault),
                List.of(opel,toyota)
        );
        assertThat(carsService.findCarsWithComponents(components))
                .containsExactlyInAnyOrderElementsOf(expectedCars.get(idx));
    }
    static Stream<Arguments> componentsFactory(){
        return Stream.of(
                Arguments.of(0,List.of("A","B", "C","D","E")),
                Arguments.of(1,List.of("A","B", "C","E","F","G")),
                Arguments.of(2,List.of("E","F","G")),
                Arguments.of(3,List.of("C")),
                Arguments.of(4,List.of("E","B")),
                Arguments.of(5,List.of("F", "H"))
        );
    }
}
