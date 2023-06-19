# КПО. Домашнее задание №4.  Система обработки заказов ресторана
## Архитектура
Реализован микросервисы авторизации и обработки заказов пользователей с помощью **Java Spring Framework**. В качестве базы данных использовался **H2** с таблицами _USERS_, _SESSION_ и _DISH_.

## Спецификация API
1) Регистрация нового пользователя (POST-запрос: http://localhost:8080/api/auth/signup) — доступно для всех пользователей;
2) Вход в пользовательский аккаунт (POST-запрос: http://localhost:8080/api/auth/login) — доступно для всех пользователей;
3) Предоставление информации о текущем авторизованном пользователе (GET-запрос: http://localhost:8080/api/info)  — доступно для всех авторизованных пользователей;
4) Изменение роли пользователя от имени менеджера (PUT-запрос: http://localhost:8080/api/change_role) — доступно только для пользователей с ролью _manager_;
5) Просмотр меню ресторана (PUT-запрос: http://localhost:8080/api/menu) — доступно для всех авторизованных пользователей.

# Документация кода
Оформленную Javadoc-документацию можно найти здесь:
* [микросервис авторизации](https://github.com/lkhorasandzhian/restaurant-order-processing-system/tree/main/generated-java-doc);
* [микросервис обработки заказов](https://github.com/lkhorasandzhian/restaurant-order-processing-system/tree/main/generated-java-doc2).

## Реализация коллекции Postman
Ссылку на Postman-коллекцию можно найти [здесь](https://www.postman.com/lkhorasandzhian/workspace/restaurant-workspace/collection/27610854-fea41a0c-277b-45f4-bcc6-9a2172d23b9b?action=share&creator=27610854).

## Примечание
* [rops](https://github.com/lkhorasandzhian/restaurant-order-processing-system/tree/main/rops) — микросервис авторизации пользователей;
* [rops2](https://github.com/lkhorasandzhian/restaurant-order-processing-system/tree/main/rops2) — микросервис обработки заказов пользователей;
* [demo](https://github.com/lkhorasandzhian/restaurant-order-processing-system/tree/main/demo) — микросервис для проверки базы данных (не предназначен для выпуска, только для разработчиков).


---
Полученная оценка за работу: 6
