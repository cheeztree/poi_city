package poicity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poicity.entity.PoiTime;
import poicity.entity.others.DAYS_OF_THE_WEEK;
import poicity.entity.others.DaysWeek;

@Repository
public interface PoiTimeRepository extends JpaRepository<PoiTime, Integer> {

//	PoiTime findByDaysWeek(DaysWeek day);
//	List<PoiTime> findAllByDays(final DaysWeek daysWeek);
	
	@Query("SELECT p FROM PoiTime p WHERE :daysWeek member of p.days")
	List<PoiTime> findasd(DaysWeek daysWeek);
	
//	@Query(nativeQuery = true, 
//			value = "SELECT * FROM poi_time")
//	List<PoiTime> findasd();
}
