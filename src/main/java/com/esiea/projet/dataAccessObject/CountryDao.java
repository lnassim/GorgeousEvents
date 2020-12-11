package com.esiea.projet.dataAccessObject;

import com.esiea.projet.model.CountryModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CountryDao {

    private static final Map<String, CountryModel> COUNTRIES_MAP = new HashMap<>();

    static {
        initDATA();
    }

    private static void initDATA() {

        CountryModel vn = new CountryModel("VN", "Vietnam");
        CountryModel en = new CountryModel("EN", "England");
        CountryModel fr = new CountryModel("FR", "France");
        CountryModel us = new CountryModel("US", "US");
        CountryModel ru = new CountryModel("RU", "Russia");

        COUNTRIES_MAP.put(vn.getCountryCode(), vn);
        COUNTRIES_MAP.put(en.getCountryCode(), en);
        COUNTRIES_MAP.put(fr.getCountryCode(), fr);
        COUNTRIES_MAP.put(us.getCountryCode(), us);
        COUNTRIES_MAP.put(ru.getCountryCode(), ru);
    }

    public CountryModel findCountryByCode(String countryCode) {
        return COUNTRIES_MAP.get(countryCode);
    }

    public List<CountryModel> getCountries() {
        List<CountryModel> list = new ArrayList<>();
        list.addAll(COUNTRIES_MAP.values());
        return list;
    }

}
