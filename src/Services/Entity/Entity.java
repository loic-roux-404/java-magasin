package Services.Entity;

import java.io.Serializable;

public interface Entity extends Serializable {
    /**
     * This method should return ordered comma list separated entity items
     */
    public String toString();

    public int getId();

    public Entity setId(int id);
}
