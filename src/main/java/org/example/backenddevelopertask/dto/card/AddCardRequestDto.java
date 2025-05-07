package org.example.backenddevelopertask.dto.card;

import java.time.LocalDate;

public record AddCardRequestDto(String cardNumber, LocalDate expireDate,
                                      String cvv) {
}
