package guru.qa.country.model;

import guru.qa.country.data.CountryEntity;

public record Country(String name, String code) {

    public static Country fromEntity(CountryEntity countryEntity) {
        return new Country(
                countryEntity.getName(),
                countryEntity.getCode());
    }
}
