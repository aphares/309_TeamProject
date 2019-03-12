/** package hello;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class PersonForm {

    @NotNull
    private List<String> list;

    public String getName(int index) {
        return this.list.get(index);
    }

    public void setName(String name, int index) {
        this.list.set(index,name);
    }

    public String toString() {
        return "Item #1: " + list.get(0)
                + "\nItem #2: " + list.get(1)
                + "\nItem #3: " + list.get(2)
                + "\nItem #4: " + list.get(3)
                + "\nItem #5: " + list.get(4)
                + "\nItem #6: " + list.get(5)
                + "\nItem #7: " + list.get(6)
                + "\nItem #8: " + list.get(7)
                + "\nItem #9: " + list.get(8)
                + "\nItem #10: " + list.get(9);
    }
}
**/

package hello;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Min(18)
    private Integer age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}