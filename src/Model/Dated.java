package Model;

import java.util.Date;

public interface Dated {
    Date getCreatedAt();

    void setCreatedAt();

    Date getUpdatedAt();

    void setUpdatedAt();
}
