package com.astrid.diaspora.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EntityLastModificationMapperTest {

    private EntityLastModificationMapper entityLastModificationMapper;

    @BeforeEach
    public void setUp() {
        entityLastModificationMapper = new EntityLastModificationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(entityLastModificationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entityLastModificationMapper.fromId(null)).isNull();
    }
}
