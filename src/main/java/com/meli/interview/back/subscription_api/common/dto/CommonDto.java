package com.meli.interview.back.subscription_api.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CommonDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
