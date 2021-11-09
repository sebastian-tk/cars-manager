package tkaczyk.sebastian.service.extensions;

import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.CarBody;
import tkaczyk.sebastian.persistence.Engine;
import tkaczyk.sebastian.persistence.Wheel;
import java.math.BigDecimal;
import java.util.List;

import static tkaczyk.sebastian.persistence.type.CarBodyColor.*;
import static tkaczyk.sebastian.persistence.type.CarBodyType.*;
import static tkaczyk.sebastian.persistence.type.EngineType.*;
import static tkaczyk.sebastian.persistence.type.TyreType.*;

public interface CarFactory {
    Car bmw = Car
            .builder()
            .model("BMW")
            .price(new BigDecimal("100000"))
            .mileage(1000)
            .engine(Engine.builder().type(DIESEL).power(4.0).build())
            .carBody(CarBody
                    .builder()
                    .color(BLACK)
                    .type(COMBI)
                    .components(List.of("A", "B", "C", "D", "E"))
                    .build())
            .wheel(Wheel
                    .builder()
                    .type(WINTER)
                    .model("PIRELLI")
                    .size(18)
                    .build())
            .build();

    Car fiat = Car
            .builder()
            .model("FIAT")
            .price(new BigDecimal("60000"))
            .mileage(300)
            .engine(Engine.builder().type(GASOLINE).power(1.6).build())
            .carBody(CarBody
                    .builder()
                    .color(BLUE)
                    .type(HATCHBACK)
                    .components(List.of("C"))
                    .build())
            .wheel(Wheel
                    .builder()
                    .type(SUMMER)
                    .model("DEBICA")
                    .size(14)
                    .build())
            .build();

    Car opel = Car
            .builder()
            .model("OPEL")
            .price(new BigDecimal("70000"))
            .mileage(1500)
            .engine(Engine.builder().type(LPG).power(1.4).build())
            .carBody(CarBody
                    .builder()
                    .color(RED)
                    .type(SEDAN)
                    .components(List.of("D", "E", "F", "G"))
                    .build())
            .wheel(Wheel
                    .builder()
                    .type(WINTER)
                    .model("CONTINENTAL")
                    .size(15)
                    .build())
            .build();

    Car mazda = Car
            .builder()
            .model("MAZDA")
            .price(new BigDecimal("80000"))
            .mileage(1300)
            .engine(Engine.builder().type(GASOLINE).power(2.0).build())
            .carBody(CarBody
                    .builder()
                    .color(SILVER)
                    .type(HATCHBACK)
                    .components(List.of("E", "F", "G"))
                    .build())
            .wheel(Wheel
                    .builder()
                    .type(SUMMER)
                    .model("CONTINENTAL")
                    .size(19)
                    .build())
            .build();

    Car mercedes = Car
            .builder()
            .model("MERCEDES")
            .price(new BigDecimal("150000"))
            .mileage(1300)
            .engine(Engine.builder().type(GASOLINE).power(2.5).build())
            .carBody(CarBody
                    .builder()
                    .color(WHITE)
                    .type(SEDAN)
                    .components(List.of("A", "E", "F", "G", "H", "I"))
                    .build())
            .wheel(Wheel
                    .builder()
                    .type(SUMMER)
                    .model("YOKOHAMA")
                    .size(17)
                    .build())
            .build();

    Car toyota = Car
            .builder()
            .model("TOYOTA")
            .price(new BigDecimal("150000"))
            .mileage(200)
            .engine(Engine.builder().type(DIESEL).power(2.2).build())
            .carBody(CarBody
                    .builder()
                    .color(BLACK)
                    .type(SEDAN)
                    .components(List.of("B", "C"))
                    .build())
            .wheel(Wheel
                    .builder()
                    .type(WINTER)
                    .model("PIRELLI")
                    .size(16)
                    .build())
            .build();
}
