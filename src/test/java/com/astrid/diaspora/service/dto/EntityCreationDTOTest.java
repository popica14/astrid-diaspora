package com.astrid.diaspora.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class EntityCreationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCreationDTO.class);
        EntityCreationDTO entityCreationDTO1 = new EntityCreationDTO();
        entityCreationDTO1.setId(1L);
        EntityCreationDTO entityCreationDTO2 = new EntityCreationDTO();
        assertThat(entityCreationDTO1).isNotEqualTo(entityCreationDTO2);
        entityCreationDTO2.setId(entityCreationDTO1.getId());
        assertThat(entityCreationDTO1).isEqualTo(entityCreationDTO2);
        entityCreationDTO2.setId(2L);
        assertThat(entityCreationDTO1).isNotEqualTo(entityCreationDTO2);
        entityCreationDTO1.setId(null);
        assertThat(entityCreationDTO1).isNotEqualTo(entityCreationDTO2);
    }
}
