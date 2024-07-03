package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(e -> new Country(
                        e.getName(),
                        e.getCode()
                ))
                .toList();
    }

    public Country addCountry(@NonNull Country country) {
        return Country.fromEntity(
                countryRepository.save(
                        CountryEntity.fromJson(country)
                ));
    }

    public Country changeCountry(UUID id, @NonNull Country country) {
        CountryEntity countryEntity = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        countryEntity = new CountryEntity(countryEntity.getId(),
                country,
                countryEntity.getCode());
        countryRepository.save(countryEntity);
        return Country.fromEntity(countryEntity);
    }
}
