package org.pmf.services.office.modules.integration.domain.factory;

import java.util.List;

/**
 * @param <M> From: The model to be translated
 * @param <T> To: The result entity
 */
public interface Factory<M, T> {
    T build(M model);

    List<T> buildMany(List<M> model);
}