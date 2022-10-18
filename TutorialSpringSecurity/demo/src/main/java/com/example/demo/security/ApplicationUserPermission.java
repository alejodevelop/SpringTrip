package com.example.demo.security;

/*  
 *  here we define permissions as enums, enums are static final objects,
 *  every enum has a constructor that pass as argument a string describing the permission
 */
public enum ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
