package tkaczyk.sebastian.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tkaczyk.sebastian.persistence.type.TyreType;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Wheel {
    TyreType type;
    String model;
    int size;
}
