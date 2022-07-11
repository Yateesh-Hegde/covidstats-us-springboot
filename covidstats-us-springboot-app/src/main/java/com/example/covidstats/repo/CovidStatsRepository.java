package com.example.covidstats.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.covidstats.entity.CovidStat;

@Repository
public interface CovidStatsRepository
		extends PagingAndSortingRepository<CovidStat, Long>, CrudRepository<CovidStat, Long> {

	/**
	 * 
	 * @param date
	 * 
	 *             this method is used while updating the dataset by scheduler
	 *             periodically
	 *             {@link com.example.covidstats.task.ScheduledDataRetriever } all
	 *             data for same date is deleted before inserting new records
	 * 
	 */
	void deleteByUpdateTimeEquals(@Param("date") Date date);

	List<CovidStat> findByCountryIgnoreCase(String string);

	Page<CovidStat> findByCountryIgnoreCase(String string, Pageable paging);

}
