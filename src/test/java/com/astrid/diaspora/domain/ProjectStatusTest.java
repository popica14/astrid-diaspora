package com.astrid.diaspora.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.astrid.diaspora.web.rest.TestUtil;

public class ProjectStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectStatus.class);
        ProjectStatus projectStatus1 = new ProjectStatus();
        projectStatus1.setId(1L);
        ProjectStatus projectStatus2 = new ProjectStatus();
        projectStatus2.setId(projectStatus1.getId());
        assertThat(projectStatus1).isEqualTo(projectStatus2);
        projectStatus2.setId(2L);
        assertThat(projectStatus1).isNotEqualTo(projectStatus2);
        projectStatus1.setId(null);
        assertThat(projectStatus1).isNotEqualTo(projectStatus2);
    }
}
