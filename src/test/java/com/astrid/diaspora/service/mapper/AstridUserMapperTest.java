package com.astrid.diaspora.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AstridUserMapperTest {

    private AstridUserMapper astridUserMapper;

    @BeforeEach
    public void setUp() {
        astridUserMapper = new AstridUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(astridUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(astridUserMapper.fromId(null)).isNull();
    }
}
