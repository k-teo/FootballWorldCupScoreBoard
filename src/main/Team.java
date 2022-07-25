package main;

public class Team {

    private final String country;

    public Team (String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Team)) {
            return false;
        }

        return this.country.equals(((Team) obj).getCountry());
    }

    @Override
    public int hashCode() {
        return country.hashCode();
    }
}
