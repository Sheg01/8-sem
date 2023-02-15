package com.test.salon.utils;

import com.test.salon.model.Order;
import com.test.salon.model.User;
import org.springframework.stereotype.Service;

@Service
public class MessageGenerator {

    public String mailMessageGenerate(User user, Order order){

        StringBuilder stringBuilder = new StringBuilder("Здравствуйте, " + user.getClientName() + "!\n");
        stringBuilder.append("Вы оформили запись в нашем салоне на услугу " + order.getServiceTypeName().getServiceGroup());
        stringBuilder.append("(" + order.getServiceTypeName().getName() + ")" + "\n");
        stringBuilder.append("Ждём вас " + order.getOrderTime() + " " + order.getOrderDate() + ".\n");
        stringBuilder.append("Ваш мастер - " + order.getMaster().getName() + " " + order.getMaster().getSurname() + ".\n");
        stringBuilder.append("С уважением,  салон DZNTS.");
        return stringBuilder.toString();

    }

    public String chequeMessageGenerate(User user, Order order){

        StringBuilder stringBuilder = new StringBuilder("Клиент: " + user.getClientName() + "!\n");
        stringBuilder.append("Услуга: " + order.getServiceTypeName().getServiceGroup());
        stringBuilder.append("(" + order.getServiceTypeName().getName() + ")" + "\n");
        stringBuilder.append("Стоимость:" + order.getServiceTypeName().getPrice() + "\n");
        stringBuilder.append("Дата: " + order.getOrderTime() + " " + order.getOrderDate() + ".\n");
        stringBuilder.append("Мастер: " + order.getMaster().getName() + " " + order.getMaster().getSurname() + ".\n");
        stringBuilder.append("Салон DZNTS.");
        return stringBuilder.toString();

    }

}
