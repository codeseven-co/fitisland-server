package happyperson.fitisland.domain.adventure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "continent")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // 대륙 이름
    private String name;
    // 대륙 이름 상세
    private String detail;
    // 대륙별 최대 단계
    private Integer max_progress;
    // 정렬
    private Integer sort;

    @OneToMany(mappedBy = "continent")
    private List<Region> regions = new ArrayList<>();

    @Builder
    public Continent(Long id, String name, String detail, Integer max_progress, Integer sort, List<Region> regions) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.max_progress = max_progress;
        this.sort = sort;
        this.regions = regions;
    }
}
