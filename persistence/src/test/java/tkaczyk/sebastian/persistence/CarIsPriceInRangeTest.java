package tkaczyk.sebastian.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tkaczyk.sebastian.persistence.type.CarBodyColor.BLACK;
import static tkaczyk.sebastian.persistence.type.CarBodyType.COMBI;
import static tkaczyk.sebastian.persistence.type.EngineType.DIESEL;
import static tkaczyk.sebastian.persistence.type.TyreType.WINTER;

public class CarIsPriceInRangeTest {
    private static Car car;

    @BeforeAll
    static void init(){
        car = Car
                .builder()
                .model("BMW")
                .price(new BigDecimal("100000"))
                .mileage(1000)
                .engine(Engine.builder().type(DIESEL).power(4.0).build())
                .carBody(CarBody
                        .builder()
                        .color(BLACK)
                        .type(COMBI)
                        .components(List.of("A", "B", "C", "D", "E","F","G"))
                        .build())
                .wheel(Wheel
                        .builder()
                        .type(WINTER)
                        .model("PIRELLI")
                        .size(18)
                        .build())
                .build();
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when startRange is null")
    public void test1(){
        assertThatThrownBy(()->car.isPriceInRange(null,new BigDecimal("10000")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Arguments cannot be null");
    }
    @Test
    @DisplayName("should throw IllegalArgumentException when finishRange is null")
    public void test2(){
        assertThatThrownBy(()->car.isPriceInRange(new BigDecimal("10000"),null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Arguments cannot be null");
    }

    @ParameterizedTest
    @DisplayName("should throw IllegalArgumentException when startRange is not positive")
    @ValueSource(strings = {
            "0",
            "-0.00001",
            "-1.0",
            "-10"
    })
    public void test3(String startValue){
        assertThatThrownBy(()->car.isPriceInRange(new BigDecimal(startValue),new BigDecimal("10000")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Arguments must be positive");
    }

    @ParameterizedTest
    @DisplayName("should throw IllegalArgumentException when finishRange is not positive")
    @ValueSource(strings = {
                    "0",
                    "-0.00001",
                    "-1.0",
                    "-10"
    })
    public void test4(String finishValue){
        assertThatThrownBy(()->car.isPriceInRange(new BigDecimal("10000"),new BigDecimal(finishValue)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Arguments must be positive");
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when startRange is greater then finishRange")
    public void test5(){
        assertThatThrownBy(()->car.isPriceInRange(new BigDecimal("10000"),new BigDecimal("5000")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid range");
    }

    @Test
    @DisplayName("should return true when price of car is in range")
    public void test6() {
        assertThat(car.isPriceInRange(new BigDecimal("90000"), new BigDecimal("110000")))
                .isTrue();
    }

    @Test
    @DisplayName("should return false when price of car is not in range")
    public void test7() {
        assertThat(car.isPriceInRange(new BigDecimal("30000"), new BigDecimal("80000")))
                .isFalse();
    }
}
