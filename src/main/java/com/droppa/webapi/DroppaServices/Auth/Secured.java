/**
 * 
 */
package com.droppa.webapi.DroppaServices.Auth;

import jakarta.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Ernest Mampana
 *
 */
@NameBinding
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Secured {}
