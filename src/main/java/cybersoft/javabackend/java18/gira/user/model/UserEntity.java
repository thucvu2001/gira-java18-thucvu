package cybersoft.javabackend.java18.gira.user.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEntity {

    @UtilityClass
    public class User {
        public static final String TABLE_NAME = "G_USER";
        public static final String USERNAME = "G_USERNAME";
        public static final String PASSWORD = "G_PASSWORD";
        public static final String EMAIL = "G_EMAIL";
        public static final String DISPLAY_NAME = "G_DISPLAY_NAME";
        public static final String FULL_NAME = "G_FULL_NAME";
        public static final String AVATAR = "G_AVATAR";
        public static final String STATUS = "G_STATUS";
        public static final String FACEBOOK_URL = "G_FACEBOOK_URL";
        public static final String MAJORITY = "G_MAJORITY";
        public static final String DEPARTMENT = "G_DEPARTMENT";
        public static final String HOBBIES = "G_HOBBIES";
    }

    @UtilityClass
    public class UserMappedUserGroup {
        public static final String USER_GROUP_MAPPED_USER = "users";
        public static final String JOIN_TABLE = "G_GROUP_USERS";
        public static final String JOIN_TABLE_GROUP_ID = "G_GROUP_ID";
        public static final String JOIN_TABLE_USERS_ID = "G_USERS_ID";
    }
}
