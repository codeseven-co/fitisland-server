package happyperson.fitisland.common.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate//자동 시간 설정
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate //Entity의 값을 변경할 때 시간이 자동으로 저장
    @Column(nullable = false)
    private LocalDateTime updatedDt;

    @LastModifiedBy
    private String modifiedBy;
}

