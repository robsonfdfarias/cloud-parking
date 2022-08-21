package br.com.infocomrobson.deploy.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class CalculaValor {
    private final static double VALOR_POR_HORA = 5.0;
    private final static double VALOR_POR_MINUTO = 0.50;
    public static double calcular(LocalDateTime dateTime1, LocalDateTime dateTime2){
        Duration duration =Duration.between(dateTime1, dateTime2);
//        return (VALOR_POR_HORA * duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(VALOR_POR_MINUTO * duration.toMinutes());
        int v = (int) (duration.toMinutes() + 1); // acrescenta 1 para quando der 1 minuto a mais ele conte esse 1 minuto
        return (VALOR_POR_MINUTO * v);
    }
}
