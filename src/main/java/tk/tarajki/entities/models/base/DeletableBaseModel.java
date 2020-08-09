package tk.tarajki.entities.models.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DeletableBaseModel extends BaseModel {
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

}
