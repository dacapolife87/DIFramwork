package org.hjjang.core.bean.loader;

import org.hjjang.core.bean.collection.BeanCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeanLoaderTest {

    @Test
    @DisplayName("Component가 제대로 로그되는지 확인")
    public void loadComponentTest() throws InstantiationException, IllegalAccessException {
        BeanLoader beanLoader = new BeanLoader();
        beanLoader.loaderComponent();

        int beanSize = BeanCollection.beanSize();
        assertThat(beanSize).isGreaterThan(0);
    }

}