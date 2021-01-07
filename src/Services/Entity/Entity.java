package Services.Entity;

import java.io.Serializable;

public interface Entity extends Serializable {
    String COMMA = ", ";
    /**
     * This method is for java components like combobox
     */
    String toString();

    /**
     * This method should return ordered comma list separated entity items
     */
    String toString(boolean list);

    int getId();

    /**
     * Used by entity manager
     * @param id
     * @return
     */
    Entity setId(int id);
}
