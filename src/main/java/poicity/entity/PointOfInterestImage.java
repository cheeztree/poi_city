package poicity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "poi_img")
public class PointOfInterestImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String PathImgPoi;

//	@ManyToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "id_poi", referencedColumnName = "id")
//	private PointOfInterest poi;

}
