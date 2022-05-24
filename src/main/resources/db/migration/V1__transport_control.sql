CREATE TABLE cars_stations_transports
(
    car_station_id BIGINT NOT NULL,
    transports_id  BIGINT NOT NULL
);

CREATE TABLE airports_transports
(
    airport_id    BIGINT NOT NULL,
    transports_id BIGINT NOT NULL
);

CREATE TABLE train_stations_transports
(
    train_station_id BIGINT NOT NULL,
    transports_id    BIGINT NOT NULL
);

CREATE TABLE transportables_cargos
(
    cargo_container_id BIGINT NOT NULL,
    cargos_id          BIGINT NOT NULL
);

CREATE TABLE transportables_passengers
(
    passenger_container_id BIGINT       NOT NULL,
    passengers_id          VARCHAR(255) NOT NULL
);

CREATE TABLE seaports_transports
(
    seaport_id    BIGINT NOT NULL,
    transports_id BIGINT NOT NULL
);

ALTER TABLE cars_stations_transports
    ADD CONSTRAINT uk_9gnt4sf4a1aipvobf6mbilqv1 UNIQUE (transports_id);

ALTER TABLE airports_transports
    ADD CONSTRAINT uk_a4n3v91jaar4ifeg536u11xbs UNIQUE (transports_id);

ALTER TABLE train_stations_transports
    ADD CONSTRAINT uk_ei4yirv9v8p7l4pkijixeydxx UNIQUE (transports_id);

ALTER TABLE transportables_cargos
    ADD CONSTRAINT uk_fe0gvql48fweoby6h0i3hat6g UNIQUE (cargos_id);

ALTER TABLE transportables_passengers
    ADD CONSTRAINT uk_o3b2i1jl0r2pbv6l8gxc0v1bf UNIQUE (passengers_id);

ALTER TABLE seaports_transports
    ADD CONSTRAINT uk_rhy0tisko8uhffg86fdenrj3y UNIQUE (transports_id);

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence AS bigint START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE airports
(
    id             BIGINT  NOT NULL,
    location       VARCHAR(255),
    name           VARCHAR(255),
    parking_places INTEGER NOT NULL,
    CONSTRAINT airports_pkey PRIMARY KEY (id)
);

CREATE TABLE car_delivery
(
    id             BIGINT NOT NULL,
    destination_id BIGINT,
    executor_id    BIGINT,
    source_id      BIGINT,
    CONSTRAINT car_delivery_pkey PRIMARY KEY (id)
);

CREATE TABLE cargo
(
    id          BIGINT  NOT NULL,
    category    INTEGER NOT NULL,
    description VARCHAR(255),
    mass        INTEGER NOT NULL,
    name        VARCHAR(255),
    origin      VARCHAR(255),
    CONSTRAINT cargo_pkey PRIMARY KEY (id)
);

CREATE TABLE cars
(
    id         BIGINT  NOT NULL,
    name       VARCHAR(255),
    on_station BOOLEAN NOT NULL,
    type       INTEGER,
    content_id BIGINT,
    CONSTRAINT cars_pkey PRIMARY KEY (id)
);

CREATE TABLE cars_stations
(
    id             BIGINT  NOT NULL,
    location       VARCHAR(255),
    name           VARCHAR(255),
    parking_places INTEGER NOT NULL,
    CONSTRAINT cars_stations_pkey PRIMARY KEY (id)
);

CREATE TABLE passenger
(
    id              VARCHAR(255) NOT NULL,
    name            VARCHAR(255),
    passenger_class INTEGER,
    passport        VARCHAR(255),
    privileges      BOOLEAN      NOT NULL,
    CONSTRAINT passenger_pkey PRIMARY KEY (id)
);

CREATE TABLE passenger_container_class_capacities
(
    passenger_container_id BIGINT  NOT NULL,
    class_capacities       INTEGER,
    class_capacities_key   INTEGER NOT NULL,
    CONSTRAINT passenger_container_class_capacities_pkey PRIMARY KEY (passenger_container_id, class_capacities_key)
);

CREATE TABLE plane_delivery
(
    id             BIGINT NOT NULL,
    destination_id BIGINT,
    executor_id    BIGINT,
    source_id      BIGINT,
    CONSTRAINT plane_delivery_pkey PRIMARY KEY (id)
);

CREATE TABLE planes
(
    id         BIGINT  NOT NULL,
    name       VARCHAR(255),
    on_station BOOLEAN NOT NULL,
    type       INTEGER,
    content_id BIGINT,
    CONSTRAINT planes_pkey PRIMARY KEY (id)
);

CREATE TABLE seaports
(
    id             BIGINT  NOT NULL,
    location       VARCHAR(255),
    name           VARCHAR(255),
    parking_places INTEGER NOT NULL,
    CONSTRAINT seaports_pkey PRIMARY KEY (id)
);

CREATE TABLE ship_delivery
(
    id             BIGINT NOT NULL,
    destination_id BIGINT,
    executor_id    BIGINT,
    source_id      BIGINT,
    CONSTRAINT ship_delivery_pkey PRIMARY KEY (id)
);

CREATE TABLE ships
(
    id         BIGINT  NOT NULL,
    name       VARCHAR(255),
    on_station BOOLEAN NOT NULL,
    type       INTEGER,
    content_id BIGINT,
    CONSTRAINT ships_pkey PRIMARY KEY (id)
);

CREATE TABLE train_delivery
(
    id             BIGINT NOT NULL,
    destination_id BIGINT,
    executor_id    BIGINT,
    source_id      BIGINT,
    CONSTRAINT train_delivery_pkey PRIMARY KEY (id)
);

CREATE TABLE train_stations
(
    id             BIGINT  NOT NULL,
    location       VARCHAR(255),
    name           VARCHAR(255),
    parking_places INTEGER NOT NULL,
    CONSTRAINT train_stations_pkey PRIMARY KEY (id)
);

CREATE TABLE trains
(
    id         BIGINT  NOT NULL,
    name       VARCHAR(255),
    on_station BOOLEAN NOT NULL,
    type       INTEGER,
    content_id BIGINT,
    CONSTRAINT trains_pkey PRIMARY KEY (id)
);

CREATE TABLE transportables
(
    type     VARCHAR(31) NOT NULL,
    id       BIGINT      NOT NULL,
    capacity INTEGER,
    CONSTRAINT transportables_pkey PRIMARY KEY (id)
);

ALTER TABLE passenger_container_class_capacities
    ADD CONSTRAINT fk1ouo0jv2macsx60ui806fb6i9 FOREIGN KEY (passenger_container_id) REFERENCES transportables (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE car_delivery
    ADD CONSTRAINT fk5l4myqfyaigsvu7gar67hlev FOREIGN KEY (executor_id) REFERENCES cars (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE train_delivery
    ADD CONSTRAINT fk7j5743vvh9c17vxwvclb2qkmi FOREIGN KEY (executor_id) REFERENCES trains (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE cars_stations_transports
    ADD CONSTRAINT fk7rj41obr7rfxhhljfei7q89o8 FOREIGN KEY (transports_id) REFERENCES cars (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE plane_delivery
    ADD CONSTRAINT fk9ruo5rtk4c217tppkkbrx5rt9 FOREIGN KEY (destination_id) REFERENCES airports (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE plane_delivery
    ADD CONSTRAINT fkae2c98u43kn5gvlehhsoynjjy FOREIGN KEY (source_id) REFERENCES airports (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE car_delivery
    ADD CONSTRAINT fkaosxi13dju8k2o9wfjxoxcert FOREIGN KEY (destination_id) REFERENCES cars_stations (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE train_delivery
    ADD CONSTRAINT fkapt1cg72erlcu810b6486xt9p FOREIGN KEY (destination_id) REFERENCES train_stations (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE plane_delivery
    ADD CONSTRAINT fkbco3j1fqpsc6qem5k5wuiv36g FOREIGN KEY (executor_id) REFERENCES planes (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ships
    ADD CONSTRAINT fkbejll31euwup8o1lyjecqlutt FOREIGN KEY (content_id) REFERENCES transportables (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE seaports_transports
    ADD CONSTRAINT fkc4tr3d6932v9q5kx8nt17m0us FOREIGN KEY (transports_id) REFERENCES ships (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ship_delivery
    ADD CONSTRAINT fkcccitw3txe7lrh57769metu1n FOREIGN KEY (destination_id) REFERENCES seaports (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE planes
    ADD CONSTRAINT fkcvfmq1piseedvc3fl4m3h6172 FOREIGN KEY (content_id) REFERENCES transportables (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE trains
    ADD CONSTRAINT fkcyxu8u4i69725chi1jlb28h8d FOREIGN KEY (content_id) REFERENCES transportables (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE airports_transports
    ADD CONSTRAINT fkd6jt5q265t3bqt1q4dwdae0ao FOREIGN KEY (airport_id) REFERENCES airports (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE train_delivery
    ADD CONSTRAINT fkeg3n6cyjjj0ri2vo5bjqr6ms5 FOREIGN KEY (source_id) REFERENCES train_stations (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ship_delivery
    ADD CONSTRAINT fkergdslicj3e79qwr18690q1an FOREIGN KEY (executor_id) REFERENCES ships (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE cars
    ADD CONSTRAINT fkgt6rcx7stjarjd5kkl51fl5dq FOREIGN KEY (content_id) REFERENCES transportables (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE train_stations_transports
    ADD CONSTRAINT fkheosoibpcqjww7634yjbwc0hh FOREIGN KEY (transports_id) REFERENCES trains (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE transportables_cargos
    ADD CONSTRAINT fkik90oognu7ryb965ek065etv1 FOREIGN KEY (cargo_container_id) REFERENCES transportables (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ship_delivery
    ADD CONSTRAINT fkinq7mgw54sig2096j5k4owatg FOREIGN KEY (source_id) REFERENCES seaports (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE train_stations_transports
    ADD CONSTRAINT fkinw98hgvshasn4s6t5m1euqbs FOREIGN KEY (train_station_id) REFERENCES train_stations (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE car_delivery
    ADD CONSTRAINT fkmdk33vnxmwcq4igg71ufh7e37 FOREIGN KEY (source_id) REFERENCES cars_stations (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE airports_transports
    ADD CONSTRAINT fkmt0r70004ek0k1rj81bfgx6q9 FOREIGN KEY (transports_id) REFERENCES planes (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE cars_stations_transports
    ADD CONSTRAINT fko4wf1aev1xatc20nymrd7ms4c FOREIGN KEY (car_station_id) REFERENCES cars_stations (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE transportables_passengers
    ADD CONSTRAINT fkotdp9m0uak6jrlib239ier897 FOREIGN KEY (passenger_container_id) REFERENCES transportables (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE transportables_cargos
    ADD CONSTRAINT fkpq5y9mj0p6wwwbmmb1ie2nd7r FOREIGN KEY (cargos_id) REFERENCES cargo (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE transportables_passengers
    ADD CONSTRAINT fkrn1deoo64pmus4p44b0u5ma7t FOREIGN KEY (passengers_id) REFERENCES passenger (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE seaports_transports
    ADD CONSTRAINT fkxlv5khgfhtsxjt5hai5l2wp9 FOREIGN KEY (seaport_id) REFERENCES seaports (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
