package com.app.Services;

import java.io.Serializable;

public interface IEntity extends Serializable {
    /**
     * This method is for java components like combobox
     */
    String toString();

    /**
     * This method should return ordered comma list separated entity items
     * Used by swing table layouts
     */
    String toString(boolean list);

    /**
     * Get Id
     * @return int
     */
    Long getId();

    /**
     * Used by entity manager
     * @param id
     * @return IEntity
     */
    IEntity setId(Long id);
}
