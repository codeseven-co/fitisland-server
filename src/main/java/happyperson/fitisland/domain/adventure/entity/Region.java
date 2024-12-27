package happyperson.fitisland.domain.adventure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // 지역 이름
    private String name;
    // 지역 설명 (현재 markdown)
    private String description;
    // 정렬
    private Integer sort;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @Builder
    public Region(Long id, String name, String description, Integer sort, Continent continent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sort = sort;
        this.continent = continent;
    }
}
