package tkaczyk.sebastian.service.type;

import lombok.*;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics<T> {
    T min;
    T average;
    T max;

    /**
     *
     * @param stats object IntSummaryStatistics
     * @return  new object Statistics type of Double with min,max,average from stats
     */
    public static Statistics<Double> toIntStatistics(IntSummaryStatistics stats){
        return Statistics.<Double>builder()
                .min((double)stats.getMin())
                .average(stats.getAverage())
                .max((double)stats.getMax())
                .build();
    }

    /**
     *
     * @param stats object DoubleSummaryStatistics
     * @return new object Statistics type of Double with min,max,average from stats
     */
    public static Statistics<Double> toDoubleStatistics(DoubleSummaryStatistics stats){
        return Statistics.<Double>builder()
                .min(stats.getMin())
                .average(stats.getAverage())
                .max(stats.getMax())
                .build();
    }

    /**
     *
     * @param stats object BigDecimalSummaryStatistics
     * @return new object Statistics type of BigDecimal with min,max,average from stats
     */
    public static Statistics<BigDecimal> toBigDecimalStatistics(BigDecimalSummaryStatistics stats){
        return Statistics.<BigDecimal>builder()
                .min(stats.getMin())
                .average(stats.getAverage())
                .max(stats.getMax())
                .build();
    }
}
