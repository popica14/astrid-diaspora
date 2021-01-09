package com.astrid.diaspora.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectStatusMapperTest {

    private ProjectStatusMapper projectStatusMapper;

    @BeforeEach
    public void setUp() {
        projectStatusMapper = new ProjectStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(projectStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(projectStatusMapper.fromId(null)).isNull();
    }
}
