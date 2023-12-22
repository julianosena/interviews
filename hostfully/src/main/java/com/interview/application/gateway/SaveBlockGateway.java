package com.interview.application.gateway;

import com.interview.application.domain.Block;

public interface SaveBlockGateway {

    Block execute(final Block block);

}
