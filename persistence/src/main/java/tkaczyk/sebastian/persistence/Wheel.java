package tkaczyk.sebastian.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Wheel {
    private String model;
    private int size;
}
