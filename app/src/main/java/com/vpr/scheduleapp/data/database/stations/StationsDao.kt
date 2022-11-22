package com.vpr.scheduleapp.data.database.stations

import androidx.room.*
import com.vpr.scheduleapp.data.database.stations.entity.*

@Dao
abstract class StationsDao{

    @Query("SELECT * FROM country")
    abstract suspend fun getAllCountries(): List<CountryEntity>

    @Query("SELECT * FROM station WHERE code = :stationCode LIMIT 1")
    abstract suspend fun getStationByCode(stationCode: String): StationEntity?

    @Query("SELECT * FROM station WHERE title = :stationName LIMIT 1")
    abstract suspend fun getStationByName(stationName: String): StationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCountries(countryList: List<CountryEntity?>)

    @Update
    abstract suspend fun update(countries: List<CountryEntity>)

    @Delete
    abstract suspend fun delete(countries: List<CountryEntity>)
}