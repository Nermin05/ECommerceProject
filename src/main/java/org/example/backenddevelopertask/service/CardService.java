package org.example.backenddevelopertask.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backenddevelopertask.dto.card.AddCardRequestDto;
import org.example.backenddevelopertask.dto.card.CardResponseDto;
import org.example.backenddevelopertask.dto.card.UpdateCardRequestDto;
import org.example.backenddevelopertask.exception.ResourceNotFoundException;
import org.example.backenddevelopertask.mapper.CardMapper;
import org.example.backenddevelopertask.model.Card;
import org.example.backenddevelopertask.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public List<CardResponseDto> getAll() {
        return cardMapper.cardsToCardsResponseDto(cardRepository.findAll());
    }

    public CardResponseDto getById(Long id) throws ResourceNotFoundException {
        Card comment = cardRepository.findById(id).orElseThrow(() -> {
            log.error("Card can not found");
            return new ResourceNotFoundException("Card can not found");
        });
        return cardMapper.cardToCardResponseDto(comment);
    }

    public CardResponseDto add(AddCardRequestDto addCardRequestDto) {
        Card save = cardRepository.save(cardMapper.addCardRequestDtoToCard(addCardRequestDto));
        return cardMapper.cardToCardResponseDto(save);
    }

    public CardResponseDto update(Long id, UpdateCardRequestDto updateCardRequestDto) throws ResourceNotFoundException {
        Card card = cardRepository.findById(id).orElseThrow(() -> {
            log.error("Card can not found");
            return new ResourceNotFoundException("Card can not found");
        });
        Card updated = cardMapper.updateCardRequestDtoToCard(updateCardRequestDto, card);

        return cardMapper.cardToCardResponseDto(updated);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }


}
