-- Пользователи
create sequence users_id_seq;
create table users (
    id              bigint          not null constraint users_pk primary key,
    login           varchar(256)    not null,
    name            varchar(256)    not null,
    password        varchar         not null,
    role            varchar(256)    not null,
    email           varchar(256)
);

-- Тренеры
create sequence coaches_id_seq;
create table coaches (
    id              bigint          not null constraint coaches_pk primary key,
    last_name       varchar(256)    not null,
    first_name      varchar(256)    not null,
    middle_name     varchar(256)    not null,
    organization_id bigint,
    deleted_at      timestamp
);

-- Организации
create sequence organizations_id_seq;
create table organizations (
    id              bigint          not null constraint organizations_pk primary key,
    name            varchar(256)    not null,
    deleted_at      timestamp
);

-- Участники
create sequence participants_id_seq;
create table participants (
    id              bigint          not null constraint participants_pk primary key,
    last_name       varchar(256)    not null,
    first_name      varchar(256)    not null,
    middle_name     varchar(256),
    gender          char            not null,
    birthday        date            not null,
    coach_id        bigint          not null constraint participants_coach_id_fk references coaches,
    deleted_at      timestamp
);

-- Карточка
create sequence cards_id_seq;
create table cards (
    id              bigint          not null constraint cards_pk primary key,
    participant_id  bigint          not null constraint cards_participant_id_fk references participants,
    planned_time    timestamp,
    real_time       timestamp,
    category        varchar(256),
    position        int,
    lane            int,
    deleted_at      timestamp
);

-- Соревнование
create sequence competitions_id_seq;
create table competitions (
    id              bigint          not null constraint competitions_pk primary key,
    name            varchar(1000)   not null,
    date            timestamp       not null,
    deleted_at      timestamp
);
