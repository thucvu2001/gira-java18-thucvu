package cybersoft.javabackend.java18.gira.user.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEntity {

    @UtilityClass
    public class User {
        public static final String TABLE_NAME = "users";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String DISPLAY_NAME = "display_name";
        public static final String FULL_NAME = "full_name";
        public static final String AVATAR = "avatar";
        public static final String STATUS = "status";
        public static final String FACEBOOK_URL = "facebook_url";
        public static final String MAJORITY = "majority";
        public static final String DEPARTMENT = "department";
        public static final String HOBBIES = "hobbies";
    }

    @UtilityClass
    public class UserMappedUserGroup {
        public static final String JOIN_TABLE = "usergroups_users";
        public static final String JOIN_TABLE_GROUP_ID = "usergroup_id";
        public static final String JOIN_TABLE_USERS_ID = "user_id";
    }
}
