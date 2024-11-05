package entity.user;

/**
 * Factory for creating users. At creation, a user's chat history is empty.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param name the name of the new user
     * @param password the password of the new user
     * @return the new user
     */
    User create(String name, String password);

}
