package happyperson.fitisland.domain.adventure.service;

import happyperson.fitisland.domain.adventure.dto.response.ContinentResponse;
import happyperson.fitisland.domain.adventure.repository.ContinentRepository;
import happyperson.fitisland.domain.adventure.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdventureService {
    private final ContinentRepository continentRepository;

    @Transactional(readOnly = true)
    public List<ContinentResponse.ContinentDto> findContinentsByProgress() {
        return continentRepository.findAll().stream()
                .map(ContinentResponse.ContinentDto::new)
                .collect(Collectors.toList());
    }
}
