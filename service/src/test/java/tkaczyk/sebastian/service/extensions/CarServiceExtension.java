package tkaczyk.sebastian.service.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import tkaczyk.sebastian.persistence.Car;
import tkaczyk.sebastian.persistence.CarBody;
import tkaczyk.sebastian.persistence.Engine;
import tkaczyk.sebastian.persistence.Wheel;
import tkaczyk.sebastian.persistence.type.CarBodyColor;
import tkaczyk.sebastian.persistence.type.CarBodyType;
import tkaczyk.sebastian.persistence.type.EngineType;
import tkaczyk.sebastian.persistence.type.TyreType;
import tkaczyk.sebastian.service.CarsService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class CarServiceExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarsService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var cars = Set.of(
                Car
                        .builder()
                        .model("BMW")
                        .price(new BigDecimal("100000"))
                        .mileage(1000)
                        .engine(Engine.builder().type(EngineType.DIESEL).power(4.0).build())
                        .carBody(CarBody
                                .builder()
                                .color(CarBodyColor.BLACK)
                                .type(CarBodyType.COMBI)
                                .components(List.of("A","B","C","D","E"))
                                .build())
                        .wheel(Wheel
                                .builder()
                                .type(TyreType.WINTER)
                                .model("PIRELLI")
                                .size(18)
                                .build())
                        .build(),
                Car
                        .builder()
                        .model("FIAT")
                        .price(new BigDecimal("60000"))
                        .mileage(300)
                        .engine(Engine.builder().type(EngineType.GASOLINE).power(1.6).build())
                        .carBody(CarBody
                                .builder()
                                .color(CarBodyColor.BLUE)
                                .type(CarBodyType.HATCHBACK)
                                .components(List.of("C"))
                                .build())
                        .wheel(Wheel
                                .builder()
                                .type(TyreType.SUMMER)
                                .model("DEBICA")
                                .size(14)
                                .build())
                        .build(),
                Car
                        .builder()
                        .model("OPEL")
                        .price(new BigDecimal("70000"))
                        .mileage(1500)
                        .engine(Engine.builder().type(EngineType.LPG).power(1.4).build())
                        .carBody(CarBody
                                .builder()
                                .color(CarBodyColor.RED)
                                .type(CarBodyType.SEDAN)
                                .components(List.of("D","E","F","G"))
                                .build())
                        .wheel(Wheel
                                .builder()
                                .type(TyreType.WINTER)
                                .model("CONTINENTAL")
                                .size(15)
                                .build())
                        .build(),
                Car
                        .builder()
                        .model("MAZDA")
                        .price(new BigDecimal("80000"))
                        .mileage(1300)
                        .engine(Engine.builder().type(EngineType.GASOLINE).power(2.0).build())
                        .carBody(CarBody
                                .builder()
                                .color(CarBodyColor.SILVER)
                                .type(CarBodyType.HATCHBACK)
                                .components(List.of("E","F","G"))
                                .build())
                        .wheel(Wheel
                                .builder()
                                .type(TyreType.SUMMER)
                                .model("CONTINENTAL")
                                .size(19)
                                .build())
                        .build(),
                Car
                        .builder()
                        .model("MERCEDES")
                        .price(new BigDecimal("150000"))
                        .mileage(1300)
                        .engine(Engine.builder().type(EngineType.GASOLINE).power(2.5).build())
                        .carBody(CarBody
                                .builder()
                                .color(CarBodyColor.WHITE)
                                .type(CarBodyType.SEDAN)
                                .components(List.of("A","E","F","G","H","I"))
                                .build())
                        .wheel(Wheel
                                .builder()
                                .type(TyreType.SUMMER)
                                .model("YOKOHAMA")
                                .size(17)
                                .build())
                        .build(),
                Car
                        .builder()
                        .model("TOYOTA")
                        .price(new BigDecimal("150000"))
                        .mileage(200)
                        .engine(Engine.builder().type(EngineType.DIESEL).power(2.2).build())
                        .carBody(CarBody
                                .builder()
                                .color(CarBodyColor.BLACK)
                                .type(CarBodyType.SEDAN)
                                .components(List.of("B","C"))
                                .build())
                        .wheel(Wheel
                                .builder()
                                .type(TyreType.WINTER)
                                .model("PIRELLI")
                                .size(16)
                                .build())
                        .build()
        );

        return new CarsService(cars);
    }
}
