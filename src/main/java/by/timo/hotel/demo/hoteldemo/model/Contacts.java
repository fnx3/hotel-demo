package by.timo.hotel.demo.hoteldemo.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Contacts {

    private String phone;
    private String email;

    public Contacts() {
    }

    public Contacts(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts contacts)) return false;

        return Objects.equals(phone, contacts.phone) && Objects.equals(email, contacts.email);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(phone);
        result = 31 * result + Objects.hashCode(email);
        return result;
    }
}
