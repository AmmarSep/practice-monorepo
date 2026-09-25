package VariousStreamPrograms;

public class EmployeeByCity
{
    public String name;
    public String id;
    public String city;

    public EmployeeByCity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public EmployeeByCity(String name, String id, String city) {
        this.name = name;
        this.id = id;
        this.city = city;
    }

    @Override
    public String toString() {
        return "EmployeeByCity{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeByCity that = (EmployeeByCity) o;

        if (!name.equals(that.name)) return false;
        if (!id.equals(that.id)) return false;
        return city.equals(that.city);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }

}
