package com.astrid.diaspora.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class EntityCreationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCreation.class);
        EntityCreation entityCreation1 = new EntityCreation();
        entityCreation1.setId(1L);
        EntityCreation entityCreation2 = new EntityCreation();
        entityCreation2.setId(entityCreation1.getId());
        assertThat(entityCreation1).isEqualTo(entityCreation2);
        entityCreation2.setId(2L);
        assertThat(entityCreation1).isNotEqualTo(entityCreation2);
        entityCreation1.setId(null);
        assertThat(entityCreation1).isNotEqualTo(entityCreation2);
    }
}
