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
      return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
