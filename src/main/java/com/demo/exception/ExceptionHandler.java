package com.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.general.ResponseStatusAdviceTrait;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@ControllerAdvice
public class ExceptionHandler implements ProblemHandling, SecurityAdviceTrait, ResponseStatusAdviceTrait {

}
