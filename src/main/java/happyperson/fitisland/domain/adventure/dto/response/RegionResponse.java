package happyperson.fitisland.domain.adventure.dto.response;

import happyperson.fitisland.domain.adventure.entity.Region;
import lombok.Builder;
import lombok.Data;

public class RegionResponse {
    @Data
    public static class RegionDto {
        private Long id;
        private String name;
        private Integer sort;

        @Builder
        public RegionDto(Region region) {
            this.id = region.getId();
            this.name = region.getName();
            this.sort = region.getSort();
        }
    }
}
