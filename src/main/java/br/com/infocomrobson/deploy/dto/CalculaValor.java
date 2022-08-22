package br.com.infocomrobson.deploy.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class CalculaValor {
    private final static int ONE_HOUR = 60;
    private final static int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    private final static double ONE_HOUR_VALUE = 5.0;
    private final static double ADDITIONAL_PER_HOUR = 2.0;
    private final static double DAY_VALUE = 20.00;

    public static double calcular(LocalDateTime dateTime1, LocalDateTime dateTime2){
        Duration duration = Duration.between(dateTime1, dateTime2);
        int minutes = (int) (duration.toMinutes() + 1); // acrescenta 1 para quando der 1 minuto a mais ele conte esse 1 minuto
        double bill = 0.0;
        if(minutes<=ONE_HOUR){
            return ONE_HOUR_VALUE;
        }
        if(minutes<=TWENTY_FOUR_HOUR){
            bill = ONE_HOUR_VALUE;
            int hours = (int) (minutes/ONE_HOUR);
            for (int i = 0; i < hours; i++){
                bill += ADDITIONAL_PER_HOUR;
            }
            return bill;
        }
        int days = (int) (minutes/TWENTY_FOUR_HOUR);
        for (int i = 0; i < days; i++){
            bill += DAY_VALUE;
        }
        return bill;
    }

//    public static double calcular(LocalDateTime dateTime1, LocalDateTime dateTime2){
//        Duration duration =Duration.between(dateTime1, dateTime2);
////        return (VALOR_POR_HORA * duration.toHours());
//        System.out.println(duration.toMinutes());
//        System.out.println(VALOR_POR_MINUTO * duration.toMinutes());
//        int v = (int) (duration.toMinutes() + 1); // acrescenta 1 para quando der 1 minuto a mais ele conte esse 1 minuto
//        return (VALOR_POR_MINUTO * v);
//    }
}
