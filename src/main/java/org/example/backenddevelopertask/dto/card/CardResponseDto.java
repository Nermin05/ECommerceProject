package org.example.backenddevelopertask.dto.card;

import java.time.LocalDate;

public record CardResponseDto(String cardNumber, LocalDate expireDate,
        String cvv) {
}
