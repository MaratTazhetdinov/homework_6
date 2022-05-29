package com.example.homework_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val country = Country("Uganda")
        country.printCountryInfo()
    }

}

class Country (name: String) {
    var countryName = name
    private val countryArea: Int = (20000..50000).random()
    private val regions: List<Region> = createRegions()
    private val countryCapital: City = makeCountryCapital()


    private fun createRegions(): List<Region> {
        val regions = mutableListOf<Region>()
        for (count in 0..7) {
            val region = Region()
            regions.add(region)
        }
        return regions
    }

    private fun makeCountryCapital(): City {
        val random = (regions.indices).random()
        regions[random].regionCapital.makeCityCountryCapital()
        return regions[random].regionCapital
    }

    fun printCountryInfo() {
        println("Country name: $countryName")
        println("${countryCapital.cityName} is Country Capital")
        println("$countryArea km2 is approximate Country Area")
        println("Number of Regions: ${regions.size}")
        println("All Region's Capitals:")
        for (region in regions) {
            println(" - ${region.regionCapital.cityName}")
        }
    }
}

class Region {
    var regionName: String = ("").getRandomName()
    var districts: List<District> = createDistricts()
    var regionCapital: City = makeRegionCapital()

    private fun createDistricts() : List<District>  {
        val districts = mutableListOf<District>()
        for (count in 0 until 5) {
            var district = District()
            districts.add(district)
        }

        return districts
    }

    private fun makeRegionCapital(): City {
        var capital = City()

        val random1 = (districts.indices).random()
        val random2 = (districts[random1].cities.indices).random()
        districts[random1].cities[random2].makeCityRegionCapital()

        for (district in districts) {
            for(city in district.cities) {
                if (city.isRegionCapital) {
                    city.makeCityRegionCapital()
                    capital = city
                }
            }
        }

        return capital
    }

//    fun printRegionInfo() {
//
//        println("Region name: $regionName")
//        print("------------------------")
//
//        for (district in districts) {
//            district.printDistrictInfo()
//            println("------------------------")
//        }
//
//    }
}

class District {
    var districtName: String = ("").getRandomName()
    var cities: List<City> = createCities()

    private fun createCities(): List<City> {
        val cities = mutableListOf<City>()
        for (count in 0 until 10) {
            var city = City()
            cities.add(city)
        }
        return cities
    }

//    fun printDistrictInfo() {
//        println("District name: $districtName")
//        println("District's cities:")
//        for (city in cities) {
//            when {
//                city.isRegionCapital -> {
//                    println("- ${city.cityName} is Region Capital")
//                }
//                city.isCountryCapital -> {
//                    println("- ${city.cityName} is Country Capital")
//                }
//                else -> {
//                    println("- ${city.cityName}")
//                }
//            }
//        }
//    }
}

class City {

    var cityName: String = ("").getRandomName()
    var isRegionCapital: Boolean = false
    var isCountryCapital: Boolean = false

    fun makeCityRegionCapital() {
        isRegionCapital = true
    }

    fun makeCityCountryCapital() {
        isCountryCapital = true
    }
}

fun String.getRandomName(): String = List(8) {
    ('A'..'Z').random()
}.joinToString("")