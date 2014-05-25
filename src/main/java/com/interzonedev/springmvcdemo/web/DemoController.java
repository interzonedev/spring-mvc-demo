package com.interzonedev.springmvcdemo.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

public abstract class DemoController {
    protected final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    /**
     * Handles any {@code java.lang.Throwable} that may be thrown during any form processing.
     * 
     * @param t - The {@code java.lang.Throwable} that was thrown.
     * @param request - The current {@code javax.servlet.http.HttpServletRequest}.
     * 
     * @return Returns a forward to the view path to the error view with the "errorMessage" attribute set to the message
     *         of the exception.
     */
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable t, HttpServletRequest request) {
        log.error("handleException", t);

        String view = resolveErrorView(request);

        String errorCode = "error.general";

        if (StringUtils.isNotBlank(t.getMessage())) {
            errorCode = t.getMessage();
        }

        ModelAndView mav = new ModelAndView(view);
        mav.addObject("errorCode", errorCode);

        return mav;
    }

    /**
     * Resolves the error view based on the value of the "json" request parameter.
     * 
     * @param request - The current {@code javax.servlet.http.HttpServletRequest}.
     * 
     * @return Returns the JSON error view if the "json" request parameter is set to true, otherwise returns the regular
     *         error view.
     */
    protected String resolveErrorView(HttpServletRequest request) {
        boolean json = Boolean.valueOf(request.getParameter("json"));

        String view = "redirect:/error";
        if (json) {
            view = "error/errorJson";
        }

        return view;
    }
}
