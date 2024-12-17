package happyperson.fitisland.common.base;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * 영속성 컨텍스트에 저장 후 BaseTimeEntity 클래스의 Auditing 기능으로 인해서
     * 트랜잭션 커밋 시점에 플러시가 호출할 때 하이버네이트가 자동으로 값을 채운다
     * 중요) 스프링 부트의 Entry 포인트인 실행 클래스에 @EnableJpaAuditing 어노테이션을 적용하여 JPA Auditing을 활성화
     */
    @CreatedBy
    private Long createdBy;
    @CreatedDate//자동 시간 설정
    private LocalDateTime createdDateTime;
    @LastModifiedBy
    private Long modifiedBy;
    @LastModifiedDate //Entity의 값을 변경할 때 시간이 자동으로 저장
    private LocalDateTime updatedDateTime;
}
