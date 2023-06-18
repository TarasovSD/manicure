# manicure

Проект Manicure.

Приложение предназначено для создания записей клиентов на прием к мастерам маникюра.

В приложении представлены 4 сущности:
- пользоватль (user);
- проводимая процедура (procedure);
- запись на прием (appointment);
- комментрарий к записи на прием (comment).

Пользователь описывается следующими свойствами:
1. Id - идентификатор;
2. Имя пользователя - String firstName;
3. Фамилия пользователя - String lastName;
4. Отчество пользователя - String patronymic;
5. Электронный почтовый адрес - String email;
6. Номер телефона - String phone;
7. Дата рождения пользователя - LocalDate birthday;
8. Флаг является ли клиент мастером - Boolean isMaster;

Процедура описывается следующими свойствами:
1. Id - идентификатор;
2. Наименование процедуры - String procedureName;
3. Описание процедуры - String description;

Запись на прием описывается следующими свойствами:
1. Id - идентификатор;
2. Клиент, записывающийся на прием - User client;
3. Мастер, производящий прием - User master;
4. Проводимая процедура Procedure procedure;
5. Время и дата начала процедуры - LocalDateTime start;
6. Время и дата окончания процедуры - LocalDateTime end;
7. Время и дата создания записи - LocalDateTime created;
8. Статус процедуры - Status status.

Поле статус может принимать следующие значения:
- CREATED - запись создана,
- APPROVED - запись одобрена мастером,
- REJECTED - запись отклонена мастером,
- RESCHEDULED - запись перенесена.

Комментарий описывается следующими свойствами:
1. Id - идентификатор;
2. Комментатор - User user;
3. Комментируемая запись на прием - Appointment appointment;
4. Текст комментария - String userText;
5. Дата и время кмментария - LocalDateTime created;

Эндпойнты Appointment:
- Create - http://localhost:8080/appointment  - создать объект appointment
- Get - http://localhost:8080/{masterId}/all - получить все записи на прием по id мастера
- Get - http://localhost:8080/appointment/{appointmentId}  - получить объект appointment по id
- Get - http://localhost:8080/{masterId}/future - получить все будущие записи на прием по id мастера
- Get - http://localhost:8080/{masterId}/past - получить прошлые все записи на прием по id мастера
- Delete - http://localhost:8080/appointment/{appointmentId}  - удалить объект appointment по id
- Patch - http://localhost:8080/appointment/{appointmentId}  - обновить объект appointment по id (обновить можно только время, дату и выполняемую процедуру)
- Patch - http://localhost:8080/appointment/{appointmentId}/resolution  - одобрить/отклонить объект appointment мастером по id

Эндпойнты User:
- Create - http://localhost:8080/user - создать объект user
- Get - http://localhost:8080/user/{userId} -  получить объект user по id
- Patch - http://localhost:8080/user/{userId} - обновить объект user
- Delete - http://localhost:8080/user/{userId} - удалить объект user по id

Эндпойнты Procedure:
- Get - http://localhost:8080/procedure/{procedureId} -  получить объект procedure по id
- Get - http://localhost:8080/procedure -  получить все объекты procedure

Эндпойнты Comment:
- Create - http://localhost:8080/comment - создать объект comment
- Get - http://localhost:8080/comment/all/{appointmentId} -  получить все объекты comment по id записи на прием
- Delete - http://localhost:8080/comment/{commentId} - удалить объект comment по id

К проекту подключены следующие зависимости:
- spring-boot-starter-validation, spring-boot-starter-web, spring-boot-starter-test, spring-boot-starter-data-jpa, hibernate-validator для работы Spring Boot и Hibernate;
- lombok для работы аннотаций (@Getter, @Setter и т.д.);
- h2 для подключения к тесовой базе H2 с целью быстрой отладки программы;
- postgresql для основной базы данных PostgreSQL.

Для запуска приложения нужен запущенный Docker.

Пошаговая инструкция запуска приложения:
1. Перейти в корневую папку приложения/убедиться, что находитесь в ней.
2. В открыть терминал на mac/командную строку в windows набрать команду docker build -t manicure .
3. Набрать команду docker-compose up -d

После поднятия контейнеров Docker с прилжением и базой данных можно проверить их работу с помощью тестов Postman. 
Для этого нужно импрортировать файл manicure.postman_collection.json в Postman и запустить коллекцию.
Файл расположен в папке postman в корне.

Основной технологический стек:
- Java 17;
- Spring Boot 3;
- Hibernate ORM;
- PostgreSQL;
- Apache Maven;
- Docker.




