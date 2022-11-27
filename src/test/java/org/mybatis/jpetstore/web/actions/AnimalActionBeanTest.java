package org.mybatis.jpetstore.web.actions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimalActionBeanTest {
    @Test
    void getSearchKeywordOutputNull() {

        // Arrange
        final AnimalActionBean animalActionBean = new AnimalActionBean();

        // Act and Assert result
        assertThat(animalActionBean.getKeyword()).isNull();

    }
}
