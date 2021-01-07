package Model;

import java.util.Date;

public interface Dated {
    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getUpdatedAt();

    void setUpdatedAt(Date updatedAt);
}
