package cybersoft.javabackend.java18.gira.role.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleEntity {

    @UtilityClass
    public static class RoleMappedOperation {
        public static final String JOIN_TABLE = "roles_operations"; // chu dung truoc (role)
        public static final String JOIN_TABLE_ROLE_ID = "role_id"; // id cua role trong bang phu
        public static final String JOIN_TABLE_OPERATION_ID = "operation_id"; // id cua operation trong bang phu
    }

    @UtilityClass
    public static class RoleMappedUserGroup {
        public static final String JOIN_TABLE = "roles_usergroups";
        public static final String JOIN_TABLE_ROLE_ID = "role_id";
        public static final String JOIN_TABLE_USER_GROUP_ID = "usergroup_id";
    }

    @UtilityClass
    public static class Role {
        public static final String TABLE_NAME = "Roles";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String CODE = "code";
    }

    @UtilityClass
    public static class UserGroup {
        public static final String TABLE_NAME = "Usergroups";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String CODE = "code";
    }

    @UtilityClass
    public static class Operation {
        public static final String TABLE_NAME = "Operations";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String CODE = "code";
        public static final String TYPE = "type";
    }

    @UtilityClass
    public static class Module {
        public static final String TABLE_NAME = "Modules";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String CODE = "code";
    }
}
