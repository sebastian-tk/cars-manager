package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.type.CarBodyType;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.extensions.CarServiceExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static tkaczyk.sebastian.persistence.type.CarBodyType.*;
import static tkaczyk.sebastian.service.extensions.CarFactory.*;

@ExtendWith(CarServiceExtension.class)
@RequiredArgsConstructor
public class CarServiceWithCarsOfRequireCarBodyTypeInRangePrice {
    private final CarsService carsService;

    private static final BigDecimal correctMinPrice = new BigDecimal("1.0");
    private static final BigDecimal correctMaxPrice = new BigDecimal("2.0");
    private static final CarBodyType correctType = COMBI;


    @Test
    @DisplayName("should throw CarServiceException when argument carBodyType is null")
    public void test1(){
        assertThatThrownBy(()->carsService.withCarsOfRequireCarBodyTypeInRangePrice(null,correctMinPrice,correctMaxPrice))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Argument CarBodyType is null");
    }

    @Test
    @DisplayName("should throw CarServiceException when argument minPrice is null")
    public void test2(){
        assertThatThrownBy(()->carsService.withCarsOfRequireCarBodyTypeInRangePrice(correctType,null,correctMaxPrice))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Invalid minPrice argument");
    }

    @Test
    @DisplayName("should throw CarServiceException when argument minPrice is negative")
    public void test3(){
        assertThatThrownBy(()->carsService.withCarsOfRequireCarBodyTypeInRangePrice(correctType,new BigDecimal("-1.0"),correctMaxPrice))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Invalid minPrice argument");
    }

    @Test
    @DisplayName("should throw CarServiceException when argument maxPrice is null")
    public void test4(){
        assertThatThrownBy(()->carsService.withCarsOfRequireCarBodyTypeInRangePrice(correctType,correctMinPrice, null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Invalid maxPrice argument");
    }

    @Test
    @DisplayName("should throw CarServiceException when argument maxPrice is negative")
    public void test5(){
        assertThatThrownBy(()->carsService.withCarsOfRequireCarBodyTypeInRangePrice(correctType,correctMinPrice,new BigDecimal("-1.0")))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Invalid maxPrice argument");
    }
    @Test
    @DisplayName("should throw CarServiceException when argument minPrice is greater then maxPrice")
    public void test6(){
        assertThatThrownBy(()->carsService.withCarsOfRequireCarBodyTypeInRangePrice(correctType,new BigDecimal("10"),new BigDecimal("1.0")))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Invalid range of price");
    }

    @ParameterizedTest
    @DisplayName("should return Cars with required CarBodyType instance in price range")
    @EnumSource(CarBodyType.class)
    public void test7(CarBodyType carBodyType){
        BigDecimal minPriceTest = BigDecimal.valueOf(50000);
        BigDecimal maxPriceTest = BigDecimal.valueOf(150000);

        var expectedCars = Map.of(
                HATCHBACK, List.of(fiat,mazda),
                COMBI,List.of(bmw,toyota),
                SEDAN,List.of(opel,mercedes)
                );

        assertThat(carsService.withCarsOfRequireCarBodyTypeInRangePrice(carBodyType,minPriceTest,maxPriceTest)
                .stream()
                .filter(car -> car.isCarBodyTypeEqual(carBodyType))
                .filter(car-> car.isPriceInRange(minPriceTest,maxPriceTest))
                .toList())
                .containsAll(expectedCars.get(carBodyType));
    }

    @ParameterizedTest
    @DisplayName("should return Cars with CarBodyType instance in required price range")
    @CsvSource({
            "0,50000,70000",
            "1,75000,90000",
            "2,92000,100000",
            "3,11000,150000"
    })
    public void test8(int idx,int minPrice,int maxPrice){
        CarBodyType carBodyType = HATCHBACK;
        List<List<Car>> expectedCars =  List.of(
                List.of(fiat),
                List.of(mazda),
                List.of(),
                List.of()
            );
        assertThat(carsService.withCarsOfRequireCarBodyTypeInRangePrice(carBodyType,new BigDecimal(minPrice),new BigDecimal(maxPrice))
                .stream()
                .filter(car -> car.isCarBodyTypeEqual(carBodyType))
                .filter(car-> car.isPriceInRange(new BigDecimal(minPrice),new BigDecimal(maxPrice)))
                .toList())
                .containsAll(expectedCars.get(idx));
    }
}
