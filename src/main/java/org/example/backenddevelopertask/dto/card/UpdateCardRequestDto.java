package org.example.backenddevelopertask.dto.card;

import java.time.LocalDate;

public record UpdateCardRequestDto(String cardNumber, LocalDate expireDate,
                                   String cvv){
}
