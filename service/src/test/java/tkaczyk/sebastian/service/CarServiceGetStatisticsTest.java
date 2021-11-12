package tkaczyk.sebastian.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tkaczyk.sebastian.service.exception.CarServiceException;
import tkaczyk.sebastian.service.extensions.CarServiceExtension;
import tkaczyk.sebastian.service.type.Statistics;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tkaczyk.sebastian.service.type.StatisticsType.*;

@ExtendWith(CarServiceExtension.class)
@RequiredArgsConstructor
public class CarServiceGetStatisticsTest {
    private final CarsService carsService;

    @Test
    @DisplayName("should throw CarServiceException when type is null")
    public void test1(){
        assertThatThrownBy(()->carsService.getStatistics(null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Argument type of StatisticsType is null");
    }

    @Test
    @DisplayName("should return correct statistics for price")
    public void test2() {
        var expectedStatistics = Statistics
                .<BigDecimal>builder()
                .min(BigDecimal.valueOf(60000))
                .average(new BigDecimal("102555.5555555555555555555555555556"))
                .max(BigDecimal.valueOf(150000))
                .build();

        assertThat(carsService.getStatistics(PRICE))
                .isEqualTo(expectedStatistics);
    }

    @Test
    @DisplayName("should return correct statistics for mileage")
    public void test3(){
        var expectedStatistics = Statistics
                .<Double>builder()
                .min(200.0)
                .average(833.3333333333334)
                .max(1500.0)
                .build();

        assertThat(carsService.getStatistics(MILEAGE))
                .isEqualTo(expectedStatistics);
    }

    @Test
    @DisplayName("should return correct statistics for power engine")
    public void test4(){
        var expectedStatistics = Statistics
                .<Double>builder()
                .min(1.4)
                .average(2.322222222222222)
                .max(4.0)
                .build();

        assertThat(carsService.getStatistics(POWER_ENGINE))
                .isEqualTo(expectedStatistics);
    }


}
