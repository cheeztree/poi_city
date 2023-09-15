package poicity.entity;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import poicity.entity.others.DAYS_OF_THE_WEEK;
import poicity.entity.others.DaysWeek;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "poi_time")
public class PoiTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalTime openingTime;
	private LocalTime closingTime;

	@ElementCollection(targetClass = DaysWeek.class)
	@JoinTable(name = "poi_time_days")
//	@Column(name = "days", nullable = false)
	@Enumerated(EnumType.STRING)
	Collection<DaysWeek> days;
	
//	@Basic
//    @Enumerated(EnumType.STRING)
//	private List<DaysWeek> days;

//    @Enumerated(EnumType.STRING)
//	private DaysWeek day;

//	@ManyToOne
//	private PointOfInterest poi;

}
