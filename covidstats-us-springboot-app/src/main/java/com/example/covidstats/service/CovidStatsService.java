package com.example.covidstats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.example.covidstats.entity.CovidStat;
import com.example.covidstats.repo.CovidStatsRepository;

/**
 * 
 * @author yateeshhegde
 *
 ** Service implementation for retrieving stat from repository serves
 * {@link com.example.covidstats.controller.CovidStatsController}
 */
@Service
public class CovidStatsService {

	@Autowired
	private CovidStatsRepository repository;

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return return paginated statics for usa , if sortBy (column_name from
	 *         {@link com.example.covidstats.entity.CovidStat}) provided sort it.
	 *         else ignore sorting.
	 */

	public Page<CovidStat> findPaginated(int pageNo, int pageSize, String sortBy) {
		Pageable paging;

		// if sort option provided then sort with parameter
		if (sortBy == null)
			paging = PageRequest.of(pageNo, pageSize);
		else
			paging = PageRequest.of(pageNo, pageSize).withSort(Sort.by(sortBy));

		Page<CovidStat> pagedResult = repository.findByCountryIgnoreCase("us", paging);

		return pagedResult;
	}

	/**
	 * 
	 * @param countryCode
	 * @return covid statics of country("US") if countryCode provided else covid
	 *         statics of world
	 */

	public List<CovidStat> getStats(String countryCode) {

		if (countryCode == null)

			return Streamable.of(repository.findAll()).toList();

		else
			return repository.findByCountryIgnoreCase("US");

	}

}
