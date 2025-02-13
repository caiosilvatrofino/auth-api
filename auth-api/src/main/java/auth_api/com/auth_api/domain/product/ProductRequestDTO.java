package auth_api.com.auth_api.domain.product;

import org.antlr.v4.runtime.misc.NotNull;


public record ProductRequestDTO(
        @NotNull
        String name,

        @NotNull
        Integer price
) {}
