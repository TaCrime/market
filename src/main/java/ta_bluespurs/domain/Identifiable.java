package ta_bluespurs.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class Identifiable  {

    @Column(updatable=false, nullable = false)
    @Id
    String id = UUID.randomUUID().toString();

    public Identifiable() {}

    public String getId() {
        return id;
    }
    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Identifiable)) {
            return false;
        }
        Identifiable other = (Identifiable) obj;
        return getId().equals(other.getId());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +"{id='" + getId() + '\'' + '}';
    }

}
