package com.astrid.diaspora.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AstridProjectSuggestionMapperTest {

    private AstridProjectSuggestionMapper astridProjectSuggestionMapper;

    @BeforeEach
    public void setUp() {
        astridProjectSuggestionMapper = new AstridProjectSuggestionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(astridProjectSuggestionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(astridProjectSuggestionMapper.fromId(null)).isNull();
    }
}
