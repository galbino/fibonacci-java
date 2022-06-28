package com.argyle.fibonacci;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
class FibonacciApplicationTests {

    @Autowired
    private FibonacciController controller;

    @Test
    public void checkWhenNIsZero() {
        BigInteger response = controller.getFibonacci(0);
        assert response.equals(new BigInteger("0"));
    }

    @Test
    public void checkWhenNIsNegative() {
        BigInteger response = controller.getFibonacci(-1);
        assert response.equals(new BigInteger("0"));
    }

    @Test
    public void checkWhenNIsValid() {
        BigInteger response = controller.getFibonacci(5);
        assert response.equals(new BigInteger("5"));
    }

}
