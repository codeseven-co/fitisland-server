package happyperson.fitisland.domain.adventure.dto.response;

import happyperson.fitisland.domain.adventure.entity.Continent;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class ContinentResponse {

    @Data
    public static class ContinentDto {
        private Long id;
        private String name;
        private String detail;
        private Integer max_progress;
        private List<RegionResponse.RegionDto> regions;

        @Builder
        public ContinentDto(Continent continent) {
            this.id = continent.getId();
            this.name = continent.getName();
            this.detail = continent.getDetail();
            this.max_progress = continent.getMax_progress();
            this.regions = continent.getRegions().stream()
                    .map(RegionResponse.RegionDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class ContinentDtoV2 {
        private Long id;
        private String name;
        private String detail;
        private Integer max_progress;

        public ContinentDtoV2(Continent continent) {
            this.id = continent.getId();
            this.name = continent.getName();
            this.detail = continent.getDetail();
            this.max_progress = continent.getMax_progress();
        }
    }
}
