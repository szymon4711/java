package entity;

import javax.persistence.*;

@Entity
@Table(name = "address_info", schema = "labhibernate", catalog = "")
public class AddressInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ai_id")
    private int aiId;
    @Basic
    @Column(name = "ai_p_id")
    private Integer aiPId;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "house_number")
    private String houseNumber;
    @Basic
    @Column(name = "town")
    private String town;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "country")
    private String country;
    @ManyToOne
    @JoinColumn(name = "ai_p_id", referencedColumnName = "p_id", insertable = false, updatable = false)
    private PersonEntity personByAiPId;

    public int getAiId() {
        return aiId;
    }

    public void setAiId(int aiId) {
        this.aiId = aiId;
    }

    public Integer getAiPId() {
        return aiPId;
    }

    public void setAiPId(Integer aiPId) {
        this.aiPId = aiPId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressInfoEntity that = (AddressInfoEntity) o;

        if (aiId != that.aiId) return false;
        if (aiPId != null ? !aiPId.equals(that.aiPId) : that.aiPId != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        if (town != null ? !town.equals(that.town) : that.town != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aiId;
        result = 31 * result + (aiPId != null ? aiPId.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public PersonEntity getPersonByAiPId() {
        return personByAiPId;
    }

    public void setPersonByAiPId(PersonEntity personByAiPId) {
        this.personByAiPId = personByAiPId;
    }
}
