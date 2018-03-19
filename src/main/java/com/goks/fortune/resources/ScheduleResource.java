package com.goks.fortune.resources;

import com.goks.fortune.api.Schedule;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/schedule")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public ScheduleResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Schedule sendSchedule(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Schedule(counter.incrementAndGet(), value);
    }
}