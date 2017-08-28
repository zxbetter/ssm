package devin.spittr.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author devin
 */
public class Spitter {
    private Long id;

    @NotNull
    @Size(min = 2, max = 30, message = "first name invalid, min 2, max 30")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30, message = "last name invalid, min 2, max 30")
    private String lastName;

    @NotNull
    @Size(min = 5, max = 16, message = "username invalid, min 5, max 16")
    private String username;

    @NotNull
    @Size(min = 5, max = 25, message = "password invalid, min 5, max 25")
    private String password;

    private String email;

    private Boolean updateByEmail;

    public Spitter() {
    }

    public Spitter(String firstName, String lastName, String username, String password) {
        this(null, firstName, lastName, username, password);
    }

    public Spitter(Long id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Spitter setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Spitter setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Spitter setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Spitter setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Spitter setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Spitter setEmail(String email) {
        this.email = email;
        return this;
    }

    public Boolean getUpdateByEmail() {
        return updateByEmail;
    }

    public Spitter setUpdateByEmail(Boolean updateByEmail) {
        this.updateByEmail = updateByEmail;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "id");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", updateByEmail=" + updateByEmail +
                '}';
    }
}
