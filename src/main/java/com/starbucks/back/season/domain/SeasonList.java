package com.starbucks.back.season.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "season_list")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class SeasonList extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    /**
     * 시즌 id
     */
    @Column(name = "season_id", nullable = false)
    private Long seasonId;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false)
    private String productUuid;

    @Builder
    public SeasonList(Long id, Long seasonId, String productUuid) {
        this.id = id;
        this.seasonId = seasonId;
        this.productUuid = productUuid;
    }

}
