package tekiela.sales.ordering;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerDetails {
    private String email;
    private String firstname;
    private String lastname;

    CustomerDetails() {};

    public CustomerDetails(String email, String firstname, String lastname) {

        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static CustomerDetails of(String email, String firstname, String lastname) {
        return new CustomerDetails(email, firstname, lastname);
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
