package com.astrid.diaspora.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class AstridProjectDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AstridProjectDTO.class);
        AstridProjectDTO astridProjectDTO1 = new AstridProjectDTO();
        astridProjectDTO1.setId(1L);
        AstridProjectDTO astridProjectDTO2 = new AstridProjectDTO();
        assertThat(astridProjectDTO1).isNotEqualTo(astridProjectDTO2);
        astridProjectDTO2.setId(astridProjectDTO1.getId());
        assertThat(astridProjectDTO1).isEqualTo(astridProjectDTO2);
        astridProjectDTO2.setId(2L);
        assertThat(astridProjectDTO1).isNotEqualTo(astridProjectDTO2);
        astridProjectDTO1.setId(null);
        assertThat(astridProjectDTO1).isNotEqualTo(astridProjectDTO2);
    }
}
