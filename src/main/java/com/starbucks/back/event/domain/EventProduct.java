package com.starbucks.back.event.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventProduct extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false)
    private String productUuid;

    /**
     * 기획전 uuid
     */
    @Column(name = "event_uuid", length = 50, nullable = false)
    private String eventUuid;

    @Builder
    public EventProduct(Long id, String productUuid, String eventUuid) {
        this.id = id;
        this.productUuid = productUuid;
        this.eventUuid = eventUuid;
    }

}
