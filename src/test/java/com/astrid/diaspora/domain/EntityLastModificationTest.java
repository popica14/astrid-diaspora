package com.astrid.diaspora.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class EntityLastModificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityLastModification.class);
        EntityLastModification entityLastModification1 = new EntityLastModification();
        entityLastModification1.setId(1L);
        EntityLastModification entityLastModification2 = new EntityLastModification();
        entityLastModification2.setId(entityLastModification1.getId());
        assertThat(entityLastModification1).isEqualTo(entityLastModification2);
        entityLastModification2.setId(2L);
        assertThat(entityLastModification1).isNotEqualTo(entityLastModification2);
        entityLastModification1.setId(null);
        assertThat(entityLastModification1).isNotEqualTo(entityLastModification2);
    }
}
