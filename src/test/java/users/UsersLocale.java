package users;

public class UsersLocale {

    //endpoints
    public static final String REGISTER_ROUTE = "/api/v1/users/register";
    public static final String LOGIN_ROUTE = "/api/v1/users/login";

    //error messages
    public static final String DUPLICATED_EMAIL_MSG = "Email is already exists in the Database";
    public static final String INCORRECT_USER_DATA_MSG = "The email and password combination is not correct, please fill a correct email and password";
}
