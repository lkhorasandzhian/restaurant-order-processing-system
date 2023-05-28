# КПО. Домашнее задание №4.  Система обработки заказов ресторана
## Архитектура
Реализован микросервис авторизации пользователей с помощью **Java Spring Framework**. В качестве базы данных использовался **H2** с таблицами _USERS_ и _SESSION_.

## Спецификация API
1) Регистрация нового пользователя (POST-запрос: http://localhost:8080/api/auth/signup) — доступно для всех пользователей;
2) Вход в пользовательский аккаунт (POST-запрос: http://localhost:8080/api/auth/login) — доступно для всех пользователей;
3) Предоставление информации о текущем авторизованном пользователе (GET-запрос: http://localhost:8080/api/info)  — доступно для всех зарегистрированных пользователей;
4) Изменение роли пользователя от имени менеджера (PUT-запрос: http://localhost:8080/api/change_role) — доступно только для пользователей с ролью _manager_.

# Документация кода
Оформленную Javadoc-документацию можно найти [здесь](https://github.com/lkhorasandzhian/restaurant-order-processing-system/tree/main/generated-java-doc).

## Реализация коллекции Postman
Ссылку на Postman-коллекцию можно найти [здесь](https://www.postman.com/lkhorasandzhian/workspace/restaurant-workspace/collection/27610854-fea41a0c-277b-45f4-bcc6-9a2172d23b9b?action=share&creator=27610854).
