package com.astrid.diaspora.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class AstridProjectSuggestionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AstridProjectSuggestionDTO.class);
        AstridProjectSuggestionDTO astridProjectSuggestionDTO1 = new AstridProjectSuggestionDTO();
        astridProjectSuggestionDTO1.setId(1L);
        AstridProjectSuggestionDTO astridProjectSuggestionDTO2 = new AstridProjectSuggestionDTO();
        assertThat(astridProjectSuggestionDTO1).isNotEqualTo(astridProjectSuggestionDTO2);
        astridProjectSuggestionDTO2.setId(astridProjectSuggestionDTO1.getId());
        assertThat(astridProjectSuggestionDTO1).isEqualTo(astridProjectSuggestionDTO2);
        astridProjectSuggestionDTO2.setId(2L);
        assertThat(astridProjectSuggestionDTO1).isNotEqualTo(astridProjectSuggestionDTO2);
        astridProjectSuggestionDTO1.setId(null);
        assertThat(astridProjectSuggestionDTO1).isNotEqualTo(astridProjectSuggestionDTO2);
    }
}
