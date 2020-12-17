package Services.Entity;

import java.io.Serializable;

public interface Entity extends Serializable {
    /**
     * This method should return ordered comma list separated entity items
     */
    public String toString();

    public Entity factory(String[] dbData);
}
