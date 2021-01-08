package com.astrid.diaspora.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AstridProjectMapperTest {

    private AstridProjectMapper astridProjectMapper;

    @BeforeEach
    public void setUp() {
        astridProjectMapper = new AstridProjectMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(astridProjectMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(astridProjectMapper.fromId(null)).isNull();
    }
}
