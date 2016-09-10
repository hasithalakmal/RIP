/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hasitha Lakmal
 */
@Component
public final class API_keyGenarator {

    private final SecureRandom random = new SecureRandom();

    public String nextAPIId() {
        return new BigInteger(130, random).toString(32);
    }
}
