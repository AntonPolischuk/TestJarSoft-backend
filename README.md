# Тестовое задание на вакансию java developer в компанию JarSoft.
Автор решения - Полищук Антон

Дата - 18 апреля 2021г.

## Постановка задачи
Текст тестового задания находится в файле Test_ java_developer_jarSoft.pdf

## Используемы стек

    Java 8
    Spring
    Maven
    MySQL
    
    
## Описание приложения
Это исходный код тестового проекта для JarSoft. Проект представляет собой REST приложение
с бэкэндом Spring Boot и фронтэндом React, который вы можете получить по ссылке [Frontend React](https://github.com/AntonPolischuk/JarSoftTest-front-react.git).
Приложение используется для управления(CRUD) рекламными баннерами и категориями.

## Тестирование приложения
Перед запуском приложения необходимо создать базу данных(MySQL) со следующими параметрами:



    url=jdbc:mysql://localhost:3306/jarsoft
    username=user
    password=user
При использовании параметров доступа к БД, отличных от указанных, необходимо их прописать в файле ***src/main/resourses/application.properties***

При повторном запуске приложения с уже созданной и заполненной БД необходимо перед запуском закомментировать строку
***spring.datasource.data=classpath*:database/populateDB.sql*** в файле ***src/main/resourses/application.properties***

По умолчанию серверный порт приложения - 8080, при необходимости можете изменить его в файле ***src/main/resourses/application.properties***,
так же в этом случае необходимо изменить в интерфейсе файлы
***src/services/BannerService.js*** и ***src/services/CategoryService.js*** прописав необходимый порт.

Чтобы получить текст баннера, используйте URL-адрес вида http: // localhost: 8080 / bid?category=<REQ_NAME>.
По умолчанию доступны следующие <REQ_NAME> для запросов:



    rock
    pop
    jazz
    metal

В ответ приложение возвращает текст баннера из категории, указанной в запросе, согласно поставленному заданию. 
