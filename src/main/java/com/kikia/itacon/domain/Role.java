package com.kikia.itacon.domain;

/**
 * USER - sells the public service to the community
 * USER_CRED - new community member register and sells the credit
 * ADMIN - create/authenticate the USER and services
 * AUDIT - audit the USER activity
 * SUPERADMIN - create/authenticate ADMIN
 * @author diambakus
 *
 */
public enum Role {
	USER, USER_CRED, ADMIN, AUDIT, SUPERADMIN
}
