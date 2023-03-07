package cybersoft.javabackend.java18.gira.common.util;

import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import cybersoft.javabackend.java18.gira.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GiraMapperTest {
    GiraMapper giraMapper = new GiraMapper();

    @Test
    void testUSerMapper() {
        UserDTO userDTO = UserDTO.builder()
                .username("Leontyne")
                .email("chisum_bretonue@loader.rl")
                .password("46G")
                .build();

        User user = giraMapper.map(userDTO, User.class);

        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
        Assertions.assertEquals(userDTO.getEmail(), user.getEmail());
        Assertions.assertEquals(userDTO.getPassword(), user.getPassword());
    }
}