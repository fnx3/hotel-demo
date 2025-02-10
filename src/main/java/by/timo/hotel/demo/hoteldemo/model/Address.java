package by.timo.hotel.demo.hoteldemo.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    private int houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;

    public Address() {
    }

    public Address(int houseNumber, String street, String city, String county, String postCode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.county = county;
        this.postCode = postCode;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;

        return houseNumber == address.houseNumber && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(county, address.county) && Objects.equals(postCode, address.postCode);
    }

    @Override
    public int hashCode() {
        int result = houseNumber;
        result = 31 * result + Objects.hashCode(street);
        result = 31 * result + Objects.hashCode(city);
        result = 31 * result + Objects.hashCode(county);
        result = 31 * result + Objects.hashCode(postCode);
        return result;
    }
}
