package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.CarUtils;
import tkaczyk.sebastian.persistence.type.CarBodyType;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.type.SortBy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
            case SIZE_WHEEL -> cars.stream().sorted(CarUtils.sortBySizeWheel).toList();
            case POWER_ENGINE -> cars.stream().sorted(CarUtils.sortByPowerEngine).toList();
            case AMOUNT_COMPONENTS -> cars.stream().sorted(CarUtils.sortByAmountComponents).toList();
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
}
