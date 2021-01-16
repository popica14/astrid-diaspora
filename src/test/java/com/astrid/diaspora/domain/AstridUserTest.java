package com.astrid.diaspora.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class AstridUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AstridUser.class);
        AstridUser astridUser1 = new AstridUser();
        astridUser1.setId(1L);
        AstridUser astridUser2 = new AstridUser();
        astridUser2.setId(astridUser1.getId());
        assertThat(astridUser1).isEqualTo(astridUser2);
        astridUser2.setId(2L);
        assertThat(astridUser1).isNotEqualTo(astridUser2);
        astridUser1.setId(null);
        assertThat(astridUser1).isNotEqualTo(astridUser2);
    }
}
