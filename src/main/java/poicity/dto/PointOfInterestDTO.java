package poicity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poicity.entity.City;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfInterestDTO {

    private Long id;
    private String name;
	private double latitude;
	private double longitude;
	private String description;
    private City city;
    
}
