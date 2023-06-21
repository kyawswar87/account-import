package com.demo.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ResourceNotFoundException extends AbstractThrowableProblem {
    private static final URI TYPE
            = URI.create("https://localhost:8080/not-found");

    public ResourceNotFoundException(String msg) {
        super(
                TYPE,
                "Not Found Data",
                Status.NOT_FOUND,
                msg);
    }
}
