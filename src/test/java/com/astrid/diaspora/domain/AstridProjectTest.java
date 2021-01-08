package com.astrid.diaspora.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class AstridProjectTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AstridProject.class);
        AstridProject astridProject1 = new AstridProject();
        astridProject1.setId(1L);
        AstridProject astridProject2 = new AstridProject();
        astridProject2.setId(astridProject1.getId());
        assertThat(astridProject1).isEqualTo(astridProject2);
        astridProject2.setId(2L);
        assertThat(astridProject1).isNotEqualTo(astridProject2);
        astridProject1.setId(null);
        assertThat(astridProject1).isNotEqualTo(astridProject2);
    }
}
