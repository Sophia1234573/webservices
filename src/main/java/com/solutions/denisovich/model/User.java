package com.solutions.denisovich.model;

import com.solutions.denisovich.validation.UniqueLogin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "person_FindByLogin", query = "select u from User u join fetch u.role where u.login = :personLogin"),
        @NamedQuery(name = "person_FindByEmail", query = "select u from User u join fetch u.role where u.email = :personEmail"),
        @NamedQuery(name = "person_FindById", query = "select u from User u join fetch u.role where u.id = :personId")
})
@XmlRootElement(name = "User")
@Entity(name = "User")
@Table(name = "person")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @UniqueLogin()
    @NotBlank(message = "Please, enter your login.")
    @Size(min = 3, message = "Login is invalid. It must be minimum 3 chars.")
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotBlank(message = "Please, enter your password!")
    @Size(min = 4, message = "Password most be minimum 4 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Transient
    private String previousPassword;

    @NotBlank(message = "Please, enter your email.")
    @Email(message = "Email is not valid. Please, try again!")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Please, enter your first name.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Please, enter your last name.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Please, enter your birthday.")
    @Column(name = "dob", nullable = false)
    private Date birthday;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String login, String password, String email, String firstName, String lastName, Date birthday, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }

    public User(Long id, String login, String password, String email, String firstName, String lastName, Date birthday, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }

    public String getPreviousPassword() {
        return previousPassword;
    }

    public void setPreviousPassword(String previousPassword) {
        this.previousPassword = previousPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dob")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, firstName, lastName, birthday, role);
    }
}
