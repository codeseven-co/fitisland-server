package happyperson.fitisland.domain.user.dto.request;

import lombok.Data;

public class UserRequest {

    @Data
    public static class Name {
        private String name;
    }

    @Data
    public static class Nickname{
        private String nickname;
    }
}
