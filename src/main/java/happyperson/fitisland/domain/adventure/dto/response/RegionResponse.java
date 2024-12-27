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

    @Data
    public static class RegionDtoV2 {
        private Long id;
        private String name;
        private String description;
        private Integer sort;
        private ContinentResponse.ContinentDtoV2 continent;

        @Builder
        public RegionDtoV2(Region region) {
            this.id = region.getId();
            this.name = region.getName();
            this.description = region.getDescription();
            this.sort = region.getSort();
            this.continent = new ContinentResponse.ContinentDtoV2(region.getContinent());
        }
    }
}
