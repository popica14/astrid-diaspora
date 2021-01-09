package com.astrid.diaspora.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class ProjectStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectStatusDTO.class);
        ProjectStatusDTO projectStatusDTO1 = new ProjectStatusDTO();
        projectStatusDTO1.setId(1L);
        ProjectStatusDTO projectStatusDTO2 = new ProjectStatusDTO();
        assertThat(projectStatusDTO1).isNotEqualTo(projectStatusDTO2);
        projectStatusDTO2.setId(projectStatusDTO1.getId());
        assertThat(projectStatusDTO1).isEqualTo(projectStatusDTO2);
        projectStatusDTO2.setId(2L);
        assertThat(projectStatusDTO1).isNotEqualTo(projectStatusDTO2);
        projectStatusDTO1.setId(null);
        assertThat(projectStatusDTO1).isNotEqualTo(projectStatusDTO2);
    }
}
