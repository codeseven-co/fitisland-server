package happyperson.fitisland.domain.exerciseguide.controller;

import happyperson.fitisland.domain.exerciseguide.repository.ExerciseGuideRepository;
import happyperson.fitisland.domain.exerciseguide.service.ExerciseGuideService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/exerciseGuide")
public class ExerciseGuideController {

    private final ExerciseGuideService exerciseGuideService;
    private final ExerciseGuideRepository exerciseGuideRepository;

    /**
     * 생성
     */
    @PostMapping
    public void create() {

    }

    /**
     * 수정
     */

    /**
     * 삭제
     */
    @DeleteMapping
    public void delete() {

    }

    /**
     * 읽기
     */
}
