package com.kikia.itacon.domain;

/**
 * USER - use the system
 * ADMIN - create/authenticate the USER and services
 * AUDIT - audit the USER activity
 * SUPERADMIN - create/authenticate ADMIN
 * @author diambakus
 *
 */
public enum Role {
	USER, ADMIN, AUDIT, SUPERADMIN
}
