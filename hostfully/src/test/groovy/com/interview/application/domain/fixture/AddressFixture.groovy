package com.interview.application.domain.fixture

import com.interview.application.domain.Address

class AddressFixture {

    static def create(Map<String, String> parameters = [
            street : "R. Sebatian 10",
            city : "Campinas",
            postalCode : "4133-234",
            country : "Portugal",
            phone : "+351933888212"]
    ) {
        return Address.builder()
                .street(parameters.street)
                .city(parameters.city)
                .postalCode(parameters.postalCode)
                .country(parameters.country)
                .phone(parameters.phone)
                .build()
    }
}

