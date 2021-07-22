package com.banger.backend.ReqResp;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String userRole;


    public JwtResponse(String jwttoken, String userRole) {
        this.jwttoken = jwttoken;
        this.userRole = userRole;
    }


    public String getToken() {
        return this.jwttoken;
    }

    public String getUserRole() {
        return userRole;
    }
}
