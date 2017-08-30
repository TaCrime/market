package ta_bluespurs.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static ta_bluespurs.utils.Assert.assertAllPresent;

@Entity
@Table(name = "request_parameter")
public class RequestParam  extends  Identifiable{

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String value;

    public RequestParam() {}

    public RequestParam(String name, String value) {
        assertAllPresent(name, value);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
