package tkaczyk.sebastian.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tkaczyk.sebastian.persistence.type.CarBodyColor;
import tkaczyk.sebastian.persistence.type.CarBodyType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CarBody {
    private CarBodyColor color;
    private CarBodyType type;
    private List<String> components;
}
