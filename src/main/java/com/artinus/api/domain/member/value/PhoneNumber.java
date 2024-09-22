package com.artinus.api.domain.member.value;

import com.artinus.api.global.exception.ArtinusAppException;
import com.artinus.api.global.exception.ExceptionCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(
        @Column(name = "phone_number")
        String value
) {
    public PhoneNumber {
        if (value.length() != 10 && value.length() != 11) {
            throw new ArtinusAppException(ExceptionCode.INVALID_VALUE);
        }
    }
}
