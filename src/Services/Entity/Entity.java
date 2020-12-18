package Services.Entity;

import java.io.Serializable;

public interface Entity extends Serializable {
    /**
     * This method should return ordered comma list separated entity items
     */
    String toString();

    int getId();

    Entity setId(int id);
}
