package tkaczyk.sebastian.service.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import tkaczyk.sebastian.service.CarsService;
import java.util.Set;

import static tkaczyk.sebastian.service.extensions.CarFactory.*;

public class CarServiceExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarsService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new CarsService(Set.of(bmw,fiat,opel,mazda,mercedes,toyota,renault,ford,honda));
    }
}
