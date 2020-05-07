package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object objeto) {
        return calculator(objeto, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return calculator(object, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }

    private BigDecimal calculator(Object object, Class ParamCalc) {
        BigDecimal total = BigDecimal.ZERO;

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(ParamCalc) && field.getType().equals(BigDecimal.class)) {
                try {
                    total = total.add((BigDecimal) field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return total;
    }
}
