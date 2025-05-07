package org.example.backenddevelopertask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backenddevelopertask.dto.card.AddCardRequestDto;
import org.example.backenddevelopertask.dto.card.CardResponseDto;
import org.example.backenddevelopertask.dto.card.UpdateCardRequestDto;
import org.example.backenddevelopertask.exception.ResourceNotFoundException;
import org.example.backenddevelopertask.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
@Slf4j
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardResponseDto>> getAll() {
        return ResponseEntity.ok(cardService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDto> getById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(cardService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CardResponseDto> add(@RequestBody AddCardRequestDto addCardRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.add(addCardRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponseDto> update(@PathVariable Long id, @RequestBody UpdateCardRequestDto updateCardRequestDto) throws ResourceNotFoundException {
        return ResponseEntity.ok(cardService.update(id, updateCardRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
