### Декомпозиция на микросервисы

#### As-Is решение:

##### Микросервисы:

- device-service - управление устройствами
- telemetry - сбор данных
- users - пользователи

##### Проблемы As-Is:

- Нет самообслуживания
- Нет поддержки других устройств
- Нет возможности введения подписки

#### To-Be рeшение:

##### Микросервисы:

UI:
- ui-admin-service - ui для сотрудников на react
- bff-admin-service - бэкенд ui-admin-service
- ui-user-service - ui для пользователей на react
- bff-user-service - бэкенд для ui-user-service

Billing:

- payment-management-service - сервис интеграции с платежными шлюзами
- billing-service - сервис управления подпиской

Identity:

- user-service - управление/регистрация пользователей
- identity-service - Single Sign On

Device/Home/Iot:

- home-service - управление локациями устройств
- device-service - управление устройствами
- telemetry-service - телеметрия
- scenario-service - управление сценариями работы устройств

##### Проблемы To-Be:

- Высокая стоимость разработки

#### Определение взаимодействия

REST взаимодействие:

- ui-admin-service, ui-user-service -> BFF
- identity-service -> user-service
....

Асинхронное взаимодействие (kafka):

- device-service -> telemetry-service
- device-service -> scenario-service
- billing-service -> payment-management-service

#### Визуализация архитектуры

**C4 — Уровень контейнеров (Containers)**
```puml
@startuml
title Диаграмма контейнеров «Тёплый дом»

top to bottom direction

!includeurl https://raw.githubusercontent.com/RicardoNiepel/C4-PlantUML/master/C4_Component.puml

Person(user, "User", "A user of the fitness club system")
Person(admin, "Administrator", "An administrator managing the system")

Container_Boundary(WarmHouseSystem, "WarmHouse System") {
  Container(UiAdminService, "Веб интерфейс для сотрудников", "React")
  Container(UiUserService, "Веб интерфейс для пользователей", "React")
  Container(BFFAdminService, "Бэкэнд для UiAdminService", "Java Spring")
  Container(BFFUserService, "Бэкэнд для UiUserService", "Java Spring")
  Container(ApiGateway, "Маршрутизирует запросы к микросервисам")
  Container(IdentityService, "Автор-я/Аутент-я пользователей")
  Container(UsersService, "Микросервис пользователей", "Java Spring")
  Container(UsersDatabase, "UsersDatabase", "PostgreSQL", "Хранит Информацию о пользователях")
  Container(BillingService, "Cервис управления подпиской", "Java Spring")
  Container(BillingDatabase, "BillingDatabase", "PostgreSQL", "Хранит Информацию о подписках")
  Container(PaymentManagementService, "Cервис интеграции с платежными шлюзами", "Java Spring")
  Container(HomeService, "Сервис управления локациями", "Java Spring")
  Container(HomeDatabase, "HomeDatabase", "PostgreSQL", "Бд для HomeService")
  Container(DeviceService, "Сервис управления устройствами", "Java Spring")
  Container(DeviceDatabase, "DeviceDatabase", "PostgreSQL", "Бд для DeviceService")
  Container(TelemetryService, "Сервис управления телеметрией", "Java Spring")
  Container(TelemetryDatabase, "TelemetryDatabase", "PostgreSQL", "Бд для TelemetryService")
  Container(ScenarioService, "Управление сценариями работы устройст", "Java Spring")
  Container(ScenarioDatabase, "ScenarioDatabase", "PostgreSQL", "Бд для ScenarioService")
  Container(Kafka, "Брокер сообщений")
  
}

System_Ext(PaymentGateway, "Payment Gateway", "Внешний API платежного шлюза")

Rel(user, UiUserService, "Пользуется системой")
Rel(admin, UiAdminService,"Управляет системой")
Rel(UiUserService, BFFUserService, "Обслуживает запросы из ui пользователей")
Rel(UiAdminService, BFFAdminService,"Обслуживает запросы из ui администраторов")
Rel(BFFUserService, ApiGateway, "Запросы к микросервисам")
Rel(BFFAdminService, ApiGateway, "Запросы к микросервисам")
Rel_R(ApiGateway, IdentityService,"Автор-я/Аутент-я пользователей")
Rel(UsersService, UsersDatabase, "База данных пользователей")
Rel(ApiGateway, UsersService, "route")
Rel(IdentityService, UsersService, "Проверка пользователя")
Rel(ApiGateway, PaymentManagementService, "route")
Rel(ApiGateway, BillingService, "route")
Rel(PaymentManagementService, PaymentGateway, "Проведение платежа")
Rel(ApiGateway, HomeService, "route")
Rel(ApiGateway, DeviceService, "route")
Rel(ApiGateway, TelemetryService, "route")
Rel(ApiGateway, ScenarioService, "route")

Rel(HomeService, HomeDatabase, "route")
Rel(DeviceService, DeviceDatabase, "route")
Rel(TelemetryService, TelemetryDatabase, "route")
Rel(ScenarioService, ScenarioDatabase, "route")
Rel(BillingService, BillingDatabase, "route")

Rel(DeviceService, Kafka, "Отправка событий об изменении состояния устройства")
Rel(Kafka, ScenarioService, "Обработка событий о состояниях устройств")
Rel(Kafka, TelemetryService, "Обработка событий о состояниях устройств")

Rel(BillingService, Kafka, "Отправка событий о финансовых операциях")
Rel(Kafka, BillingService, "Обработка результата выполнения финансовой операции")
Rel(Kafka, PaymentManagementService, "Обработка финансовых операций")
Rel(PaymentManagementService, Kafka, "Отправка результатов выполнения финансовых операций")

@enduml
```

**C4 — Уровень компонентов (Components)**
```puml
@startuml
title Диаграмма компонентов микросервиса Device Service

top to bottom direction

!includeurl https://raw.githubusercontent.com/RicardoNiepel/C4-PlantUML/master/C4_Component.puml

Container_Boundary(WarmHouse, "WarmHouse System") {
Container(BFFUserService, "BFFUserService", "Java Spring", "Бэкэнд для фронтенда")
Container(BFFAdminService, "BFFAdminService", "Java Spring", "Бэкэнд для фронтенда")
}

Container(WebApp, "Web Application", "Java, Spring") {
Component(DeviceController, "DeviceController", "Эндпоинты для управления устройствами")
Component(ModuleController, "ModuleController", "Эндпоинты для управления модулями устройств")
Component(DeviceService, "Device Service Layer", "Бизнес логика управления устройствами")
Component(ModuleService, "Module Service Layer", "Бизнес логика управления модулями устройств")
Component(DeviceRepository, "Device Repository Layer", "Слой репозитория Device")
Component(ModuleRepository, "Module Repository Layer", "Слой репозитория Module")
}

Rel(BFFUserService,DeviceController,"Вызывает бизнес логику")
Rel(BFFUserService,ModuleController,"Вызывает бизнес логику")
Rel(BFFAdminService,DeviceController,"Вызывает бизнес логику")
Rel(BFFAdminService,ModuleController,"Вызывает бизнес логику")

Rel(DeviceController,DeviceService,"Вызывает бизнес логику")
Rel(ModuleController,ModuleService,"Вызывает бизнес логику")
Rel(DeviceService,DeviceRepository,"Reads/Writes data")
Rel(ModuleService,ModuleRepository,"Reads/Writes data")
@enduml
```

**C4 — Уровень кода (Code)**
```puml
@startuml
title Диаграмма кода микросервиса Device Service

top to bottom direction

!includeurl https://raw.githubusercontent.com/RicardoNiepel/C4-PlantUML/master/C4_Component.puml

enum DeviceType {
    TEMPERATURE
    LIGHT
    SOCKET
}

enum Status {
    TEMPERATURE
    LIGHT
    SOCKET
}

class Device {
    +Long id
    +Long houseId
    +String name
    -DeviceType deviceType
    -Status status
    +List<Module> modules
}



class Module {
    +Long id
    +String unit
    +String value
    +Device device
    +Date createdAt
    +Date modifiedAt
}

Device "1" -- "0..*" Module : has
Device --> DeviceType
Device --> Status

@enduml
```