package com.astrid.diaspora.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class EntityLastModificationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityLastModificationDTO.class);
        EntityLastModificationDTO entityLastModificationDTO1 = new EntityLastModificationDTO();
        entityLastModificationDTO1.setId(1L);
        EntityLastModificationDTO entityLastModificationDTO2 = new EntityLastModificationDTO();
        assertThat(entityLastModificationDTO1).isNotEqualTo(entityLastModificationDTO2);
        entityLastModificationDTO2.setId(entityLastModificationDTO1.getId());
        assertThat(entityLastModificationDTO1).isEqualTo(entityLastModificationDTO2);
        entityLastModificationDTO2.setId(2L);
        assertThat(entityLastModificationDTO1).isNotEqualTo(entityLastModificationDTO2);
        entityLastModificationDTO1.setId(null);
        assertThat(entityLastModificationDTO1).isNotEqualTo(entityLastModificationDTO2);
    }
}
