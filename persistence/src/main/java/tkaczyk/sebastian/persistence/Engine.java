package tkaczyk.sebastian.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tkaczyk.sebastian.persistence.type.EngineType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Engine {
    private EngineType type;
    private double power;
}
