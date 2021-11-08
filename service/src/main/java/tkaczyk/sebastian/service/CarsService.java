package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.CarUtils;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.type.SortBy;

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

}
