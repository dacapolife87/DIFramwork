package org.hjjang.core.handler;

import org.hjjang.core.bean.collection.BeanCollection;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RequestHandlerMapperTest {

    @Test
    public void registRequsetHandlerMapper() {

        RequestHandlerMapper requestHandlerMapper  = new RequestHandlerMapper();
        requestHandlerMapper.registRequestMapper();

        int handlerSize = BeanCollection.requestHandlerSize();

        assertThat(handlerSize).isGreaterThan(0);

    }

}