package happyperson.fitisland.domain.adventure.controller;

import happyperson.fitisland.domain.adventure.dto.response.ContinentResponse;
import happyperson.fitisland.domain.adventure.service.AdventureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/adventure")
public class AdventureController {
    private final AdventureService adventureService;

    @GetMapping("/continent")
    public ResponseEntity<List<ContinentResponse.ContinentDto>> findContinentsByProgress() {
        return ResponseEntity.ok(adventureService.findContinentsByProgress());
    }
}
