package com.artinus.api.global.config.querydsl;

import com.artinus.api.domain.member.value.PhoneNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {
    @Override
    public String convertToDatabaseColumn(PhoneNumber attribute) {
        if (attribute == null) return null;
        return attribute.value();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String data) {
        if (data == null) return null;
        return new PhoneNumber(data);
    }
}

