package com.example.covidstats.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.covidstats.model.CovidStatsRow;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * {@link com.example.covidstats.repo.CovidStatsRepository}
 * 
 * entity for persisting response of covid-api.
 */


@Entity
@Table(name = "covid_stats")

public class CovidStat {

	public CovidStat() {
		super();
	}

	/**
	 * 
	 * @param covidStatsRow
	 * 
	 * This constructor is used to convert api response to storable and required format
	 * 
	 */
	public CovidStat(CovidStatsRow covidStatsRow) {

		city = covidStatsRow.getCity();
		province = covidStatsRow.getProvince();
		country = covidStatsRow.getCountry();
		lastUpdate = covidStatsRow.getLastUpdate();
		keyId = covidStatsRow.getKeyId();
		confirmed = covidStatsRow.getConfirmed();
		deaths = covidStatsRow.getDeaths();
		recovered = covidStatsRow.getRecovered();
		updateTime = new Date(System.currentTimeMillis());

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	Long id;

	String city;

	String province;

	String country;

	String lastUpdate;

	String keyId;

	String confirmed;

	String deaths;

	String recovered;

	Date updateTime;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public String getDeaths() {
		return deaths;
	}

	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

}
