package poicity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfInterestDTO {

    private Long id;
    private String name;
	private String description;
    private long id_city;
    private List<Long> id_img;
    
}
