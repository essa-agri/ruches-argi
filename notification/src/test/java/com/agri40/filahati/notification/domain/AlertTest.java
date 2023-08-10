package com.agri40.filahati.notification.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.agri40.filahati.notification.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AlertTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alert.class);
        Alert alert1 = new Alert();
        alert1.setId("id1");
        Alert alert2 = new Alert();
        alert2.setId(alert1.getId());
        assertThat(alert1).isEqualTo(alert2);
        alert2.setId("id2");
        assertThat(alert1).isNotEqualTo(alert2);
        alert1.setId(null);
        assertThat(alert1).isNotEqualTo(alert2);
    }
}
