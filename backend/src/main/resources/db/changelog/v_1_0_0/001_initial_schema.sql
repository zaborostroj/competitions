-- Пользователи
create sequence public.users_id_seq;
create table public.users (
    id              serial          not null constraint users_pk primary key,
    login           varchar(256)    not null,
    first_name      varchar(256),
    last_name       varchar(256),
    email           varchar(256),
    password        varchar         not null,
    enabled         boolean
);

-- Роли пользователей
create sequence public.roles_id_seq;
create table public.roles (
    id      serial      not null constraint roles_pk primary key,
    name    varchar(256)
);

create table public.users_roles (
    user_id integer,
    role_id integer
);
alter table public.users_roles
    add constraint users_roles_users_fk foreign key (user_id)
    references public.users (id) match simple
    on update cascade
    on delete cascade;
alter table public.users_roles
    add constraint users_roles_roles_fk foreign key (role_id)
    references public.roles (id) match simple
    on update cascade
    on delete cascade;
insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN');

-- Тренеры
create sequence public.coaches_id_seq;
create table public.coaches (
    id              bigint          not null constraint coaches_pk primary key,
    last_name       varchar(256)    not null,
    first_name      varchar(256)    not null,
    middle_name     varchar(256)    not null,
    organization_id bigint,
    deleted_at      timestamp
);

-- Организации
create sequence public.organizations_id_seq;
create table public.organizations (
    id              bigint          not null constraint organizations_pk primary key,
    name            varchar(256)    not null,
    deleted_at      timestamp
);

-- Участники
create sequence public.participants_id_seq;
create table public.participants (
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
create sequence public.cards_id_seq;
create table public.cards (
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
create sequence public.competitions_id_seq;
create table public.competitions (
    id              bigint          not null constraint competitions_pk primary key,
    name            varchar(1000)   not null,
    date            timestamp       not null,
    deleted_at      timestamp
);
