package com.astrid.diaspora.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EntityCreationMapperTest {

    private EntityCreationMapper entityCreationMapper;

    @BeforeEach
    public void setUp() {
        entityCreationMapper = new EntityCreationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(entityCreationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entityCreationMapper.fromId(null)).isNull();
    }
}
