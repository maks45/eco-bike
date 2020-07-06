package maksym.durov.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchBikeDto {
    private String prefix;
    private String brand;
    private Integer weight;
    private Boolean backAndFrontLights;
    private String color;
    private Integer price;
    private Integer maxSpeed;
    private Integer batteryCapacity;
    private Integer wheelsSize;
    private Integer gearsNumber;
}
