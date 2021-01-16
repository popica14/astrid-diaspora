package com.astrid.diaspora.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class AstridUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AstridUserDTO.class);
        AstridUserDTO astridUserDTO1 = new AstridUserDTO();
        astridUserDTO1.setId(1L);
        AstridUserDTO astridUserDTO2 = new AstridUserDTO();
        assertThat(astridUserDTO1).isNotEqualTo(astridUserDTO2);
        astridUserDTO2.setId(astridUserDTO1.getId());
        assertThat(astridUserDTO1).isEqualTo(astridUserDTO2);
        astridUserDTO2.setId(2L);
        assertThat(astridUserDTO1).isNotEqualTo(astridUserDTO2);
        astridUserDTO1.setId(null);
        assertThat(astridUserDTO1).isNotEqualTo(astridUserDTO2);
    }
}
