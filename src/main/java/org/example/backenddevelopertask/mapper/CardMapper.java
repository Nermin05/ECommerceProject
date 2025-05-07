package org.example.backenddevelopertask.mapper;

import org.example.backenddevelopertask.dto.card.AddCardRequestDto;
import org.example.backenddevelopertask.dto.card.CardResponseDto;
import org.example.backenddevelopertask.dto.card.UpdateCardRequestDto;
import org.example.backenddevelopertask.model.Card;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardResponseDto cardToCardResponseDto(Card card);

    List<CardResponseDto> cardsToCardsResponseDto(List<Card> cards);

    Card addCardRequestDtoToCard(AddCardRequestDto addCardRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Card updateCardRequestDtoToCard(UpdateCardRequestDto updateCardRequestDto,@MappingTarget Card card);

}
