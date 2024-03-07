public class User {
    private String username;
    private String password;
    private String id_card;
    private String phone_number;


    public User() {
    }

    public User(String username, String password, String id_card, String phone_number) {
        this.username = username;
        this.password = password;
        this.id_card = id_card;
        this.phone_number = phone_number;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return id_card
     */
    public String getId_card() {
        return id_card;
    }

    /**
     * 设置
     * @param id_card
     */
    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    /**
     * 获取
     * @return phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * 设置
     * @param phone_number
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + ", id_card = " + id_card + ", phone_number = " + phone_number + "}";
    }
}
