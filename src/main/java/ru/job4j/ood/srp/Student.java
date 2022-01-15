package ru.job4j.ood.srp;

/**
 * 3 пример.
 *
 * Смешение архитектурных слоев.
 * Данный класс демонстриует сохранение студента в БД.
 * Но, методы публичные и демонтрируют разное поведение.
 *
 * Контроллер отвечает за эндпоинт, на который будет приходть реквест на сохранение студента.
 * Сервис будет вызываться в контроллере и отвечать за бизнесс-логику сохранинения студента.
 * Репозиторий будет сохранять студента. (Работать напрямую с БД).
 *
 * Соответственно, srp нарушен, т.к. у класса нет единой ответствнности.
 * Рациональнее всего разнести методы по соотвествующим слоям приложения.
 * Для взаимодействия с фронтом или реализации API a приложение слой контроллеров (saveUserController).
 * Для бизнесс логики слой сервисов (aveUserService())
 * Для DAO использовать репозитории (saveUserRepository()).
 */
public class Student {

    public void saveUserController() {
        saveUserService();
    }

    public void saveUserService() {
        saveUserRepository();
    }

    public void saveUserRepository() {

    }

}
