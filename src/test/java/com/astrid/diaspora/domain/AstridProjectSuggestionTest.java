package com.astrid.diaspora.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class AstridProjectSuggestionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AstridProjectSuggestion.class);
        AstridProjectSuggestion astridProjectSuggestion1 = new AstridProjectSuggestion();
        astridProjectSuggestion1.setId(1L);
        AstridProjectSuggestion astridProjectSuggestion2 = new AstridProjectSuggestion();
        astridProjectSuggestion2.setId(astridProjectSuggestion1.getId());
        assertThat(astridProjectSuggestion1).isEqualTo(astridProjectSuggestion2);
        astridProjectSuggestion2.setId(2L);
        assertThat(astridProjectSuggestion1).isNotEqualTo(astridProjectSuggestion2);
        astridProjectSuggestion1.setId(null);
        assertThat(astridProjectSuggestion1).isNotEqualTo(astridProjectSuggestion2);
    }
}
