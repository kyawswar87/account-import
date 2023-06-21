package com.demo.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidDataException extends AbstractThrowableProblem {
    private static final URI TYPE
            = URI.create("https://localhost:8080/bad-request");

    public InvalidDataException(String msg) {
        super(
                TYPE,
                "Invalid Data",
                Status.BAD_REQUEST,
                msg);
    }
}
