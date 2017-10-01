package ta.market.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static ta.market.utils.Assert.assertAllPresent;

@Entity
@Table(name = "request_parameter",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"location_id", "name"}))
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
