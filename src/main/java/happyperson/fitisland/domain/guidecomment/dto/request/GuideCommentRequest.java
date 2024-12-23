package happyperson.fitisland.domain.guidecomment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class GuideCommentRequest {
    private Long exerciseGuideId;
    private Long parentCommentId; // null이면 댓글, null이 아니면 대댓글
    private String comment;
}
