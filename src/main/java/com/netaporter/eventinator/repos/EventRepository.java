package com.netaporter.eventinator.repos;

import com.netaporter.eventinator.event.AbstractEvent;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * User: gawain
 */
public interface EventRepository extends CrudRepository<AbstractEvent, String> {

    public List<AbstractEvent> getByDomainObjectId(Serializable id);
}
