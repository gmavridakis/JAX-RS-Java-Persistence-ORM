package gr.aueb.mscis.gas.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Address {
	
	@Column(name="street", length=50)
    private String street;
    
    @Column(name="number", length = 10)
    private String number;
    
    @Column(name="city", length = 50)
    private String city;
    
    @Column(name="areacode", length = 50)
    private String areaCode;
    
    @Column(name="country", length=50)
    private String country = "Ελλάδα";

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Address() { }

    /**
     * Βοηθητικός κατασκευαστής που αντιγράφει κάποιας άλλη διεύθυνση.
     * @param address Η άλλη διεύθυνση
     */
    
    public Address(String street, String number, String city, String areaCode, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.areaCode = areaCode;
        this.country = country;
    }
    
    public Address(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.areaCode = address.getAreaCode();
        this.country = address.getCountry();
    }

    /**
     * Θέτει την οδό.
     * @param street Η οδός
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Επιστρέφει την οδό.
     * @return Η οδός
     */
    public String getStreet() {
        return street;
    }

    /**
     * Θέτει τον αριθμό.
     * @param number Ο αριθμός
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Επιστρέφει τον αριθμό.
     * @return Ο αριθμός
     */
    public String getNumber() {
        return number;
    }

    /**
     * Θέτει την πόλη.
     * @param city Η πόλη
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Επιστρέφει την πόλη.
     * @return Η πόλη
     */
    public String getCity() {
        return city;
    }

    /**
     * Θέτει τον ταχυδρομικό κώδικα.
     * @param zipcode Ο ταχυδρομικός κώδικας
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * Επιστρέφει τον ταχυδρομικό κώδικα.
     * @return Ο ταχυδρομικός κώδικας
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Θέτει τη χώρα.
     * @param country Η χώρα
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Επιστρέφει τη χώρα.
     * @return Η χώρα
     */
    public String getCountry() {
        return country;
    }

    /**
     * Η ισότητα βασίζεται σε όλα τα πεδία της διεύθυνσης.
     * @param other Το άλλο αντικείμενο προς έλεγχο
     * @return  {@code true} αν τα αντικείμενα είναι ίσα
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Address)) {
            return false;
        }

        Address theAddress = (Address) other;
        if (!(street == null ? theAddress.street 
                == null : street.equals(theAddress.street))) {
            return false;
        }
        if (!(number == null ? theAddress.number 
                == null : number.equals(theAddress.number))) {
            return false;
        }
        if (!(city == null ? theAddress.city 
                == null : city.equals(theAddress.city))) {
            return false;
        }
        if (!(areaCode == null ? theAddress.areaCode
                == null : areaCode.equals(theAddress.areaCode))) {
            return false;
        }
        if (!(country == null ? theAddress.country
                == null : country.equals(theAddress.country))) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        if (street == null && number == null && city == null
                && areaCode == null && country == null) {
            return 0;
        }

        int result = 0;
        result = street == null ? result : 13 * result + street.hashCode(); 
        result = number == null ? result : 13 * result + number.hashCode();
        result = city == null ? result : 13 * result + city.hashCode();
        result = areaCode == null ? result : 13 * result + areaCode.hashCode();
        result = country == null ? result : 13 * result + country.hashCode();
        return result;
    }


}
