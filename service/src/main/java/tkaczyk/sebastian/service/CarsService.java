package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.eclipse.collections.impl.collector.Collectors2;
import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.CarUtils;
import tkaczyk.sebastian.persistence.type.CarBodyType;
import tkaczyk.sebastian.persistence.type.EngineType;
import tkaczyk.sebastian.persistence.type.TyreType;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.type.SortBy;
import tkaczyk.sebastian.service.type.Statistics;
import tkaczyk.sebastian.service.type.StatisticsType;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.Integer.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;
import static tkaczyk.sebastian.persistence.CarUtils.*;
import static tkaczyk.sebastian.service.type.Statistics.*;

@RequiredArgsConstructor

public class CarsService {
    private final Set<Car> cars;

    /**
     *
     * @param sortBy object SortBy
     * @param descending    value boolean as flag
     * @return sorted descending List <Car> by type sorBy when flag descending is true, if false sort ascending
     */
    public List<Car> sortBy(SortBy sortBy, boolean descending){
        if(sortBy == null){
            throw new CarServiceException("Argument sortBy is null");
        }
        return  switch (sortBy){
            case SIZE_WHEEL -> descending ? cars.stream().sorted(sortBySizeWheel.reversed()).sorted(sortByModel).toList():
                                            cars.stream().sorted(sortBySizeWheel).sorted(sortByModel).toList();
            case POWER_ENGINE -> descending ?   cars.stream().sorted(sortByPowerEngine.reversed()).sorted(sortByModel).toList():
                                                cars.stream().sorted(sortByPowerEngine).sorted(sortByModel).toList();
            case AMOUNT_COMPONENTS -> descending ?  cars.stream().sorted(sortByAmountComponents.reversed()).sorted(sortByModel).toList():
                                                    cars.stream().sorted(sortByAmountComponents).sorted(sortByModel).toList();
        };
    }

    /**
     *
     * @param carBodyType object CarBodyType
     * @param minPrice  object BigDecimal as minPrice
     * @param maxPrice  object BigDecimal as maxPrice
     * @return  List with Cars with carBodyType and their price is in range between minPrice and maxPrice
     */
    public List<Car> withCarsOfRequireCarBodyTypeInRangePrice(CarBodyType carBodyType, BigDecimal minPrice, BigDecimal maxPrice){
        if(carBodyType == null){
            throw new CarServiceException("Argument CarBodyType is null");
        }
        if(minPrice == null || minPrice.compareTo(BigDecimal.ZERO)<0){
            throw new CarServiceException("Invalid minPrice argument");
        }
        if(maxPrice == null || maxPrice.compareTo(BigDecimal.ZERO)<0){
            throw new CarServiceException("Invalid maxPrice argument");
        }
        if(maxPrice.compareTo(minPrice)<0){
            throw new CarServiceException("Invalid range of price");
        }

        return cars
                    .stream()
                    .filter(car-> car.isCarBodyTypeEqual(carBodyType))
                    .filter(car-> car.isPriceInRange(minPrice,maxPrice))
                    .toList();
    }

    /**
     *
     * @param engineType object EngineType
     * @return  sorted Cars by name only with engineType equals with argument of method engineType
     */
    public List<Car> withSortedCarsByNameWithEngineType (EngineType engineType){
        if(engineType == null){
            throw new CarServiceException("Argument engineType is null");
        }
        return cars
                .stream()
                .filter(car->carWithEngineType.test(car,engineType))
                .sorted(sortByModel)
                .toList();
    }

    /**
     *
     * @param requireType object StatisticsType
     * @return a new Statistics object with type equals requireType based on cars
     */
    public Statistics<?> getStatistics(StatisticsType requireType){
        if(requireType == null){
            throw new CarServiceException("Argument type of StatisticsType is null");
        }

        return  switch (requireType){
            case PRICE -> toBigDecimalStatistics(cars.stream().collect(Collectors2.summarizingBigDecimal(toPrice)));
            case MILEAGE -> toIntStatistics(cars.stream().collect(Collectors.summarizingInt(toMileage2)));
            case POWER_ENGINE -> toDoubleStatistics(cars.stream().collect(Collectors.summarizingDouble(toPowerEngine)));
        };
    }

    /**
     *
     * @return Map with Cars as key and mileages of cars as values. Map is sorted descending by values
     */
    public Map<Car,Integer> groupByCarWithMileage(){
        return cars
                .stream()
                .collect(groupingBy(
                        identity(),
                        collectingAndThen(
                                mapping(toMileage, toList()),
                                list -> list.get(0))))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> compare(e2.getValue(), e1.getValue()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    /**
     *         Method group cars by TyreType and sorted by amount of cars in List<Car>
     * @return Map with TyreType as keys and List with Cars as values. Pairs key-values are sorted by amount cars
     *          in list descending
     */
    public Map<TyreType, List<Car>> groupByTyreType() {
        return cars
                .stream()
                .collect(groupingBy(
                        toTyreType,
                        collectingAndThen(
                                mapping(identity(), toList()),
                                l -> l.stream()
                                        .sorted(sortByModel)
                                        .toList()
                        )))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> compare(e2.getValue().size(), e1.getValue().size()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    /**
     *
     * @param components reference to List<String>
     * @return  List with  cars which contain components. Cars are sorted by name
     */
    public List<Car> findCarsWithComponents(List<String> components){
        if(components == null || components.isEmpty()){
            throw new CarServiceException("Invalid component list argument");
        }
        return cars
                .stream()
                .filter(car -> carWithComponents.test(car,components))
                .sorted(sortByModel)
                .toList();
    }

}








