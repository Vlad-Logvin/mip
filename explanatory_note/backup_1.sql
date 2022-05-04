create table if not exists authorities
(
    id        bigint generated by default as identity
        primary key,
    authority varchar(255)
        constraint uk_q0u5f2cdlshec8tlh6818bhbk
            unique
);

alter table authorities
    owner to postgres;

create table if not exists pharmacies
(
    id         bigint generated by default as identity
        primary key,
    is_deleted boolean,
    name       varchar(255)
);

alter table pharmacies
    owner to postgres;

create table if not exists reports
(
    id         bigint generated by default as identity
        primary key,
    end_date   date,
    is_deleted boolean,
    name       varchar(255),
    start_date date
);

alter table reports
    owner to postgres;

create table if not exists roles
(
    id   bigint generated by default as identity
        primary key,
    role varchar(255)
        constraint uk_g50w4r0ru3g9uf6i6fr4kpro8
            unique
);

alter table roles
    owner to postgres;

create table if not exists m2m_roles_authorities
(
    role_id      bigint not null
        constraint fkdcrcjhb90ko6y4lbpe6rf0v2y
            references roles,
    authority_id bigint not null
        constraint fk2gr117dfe5m0m99v2hcw7e8sv
            references authorities,
    primary key (role_id, authority_id)
);

alter table m2m_roles_authorities
    owner to postgres;

create table if not exists storages
(
    id               bigint generated by default as identity
        primary key,
    is_deleted       boolean,
    pharmacy_address varchar(512),
    pharmacy_id      bigint not null
        constraint fkjjbh35kf48wy1y6ay9grshjsg
            references pharmacies
);

alter table storages
    owner to postgres;

create table if not exists users
(
    id                 bigint generated by default as identity
        primary key,
    address            varchar(512),
    email              varchar(255)
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    is_deleted         boolean,
    name               varchar(64),
    encrypted_password varchar(64),
    surname            varchar(64)
);

alter table users
    owner to postgres;

create table if not exists m2m_users_roles
(
    user_id bigint not null
        constraint fk6mg7oaqihy1llaw86op7l9ovu
            references users,
    role_id bigint not null
        constraint fkhtyyclwasqeuf22msf5cal3wk
            references roles,
    primary key (user_id, role_id)
);

alter table m2m_users_roles
    owner to postgres;

create table if not exists receipts
(
    id               bigint generated by default as identity
        primary key,
    date_of_purchase date,
    grand_total      numeric(18, 2),
    is_deleted       boolean,
    solder_id        bigint not null
        constraint fki9pu9oufjwlcpu78ft483m6ga
            references users
);

alter table receipts
    owner to postgres;

create table if not exists m2m_reports_receipts
(
    report_id  bigint not null
        constraint fkl30lii3uhshmpdva0a6p1fxnx
            references reports,
    receipt_id bigint not null
        constraint fkdk6s3jtg4ria5vjygx2x75s9i
            references receipts,
    primary key (report_id, receipt_id)
);

alter table m2m_reports_receipts
    owner to postgres;

create table if not exists vendors
(
    id         bigint generated by default as identity
        primary key,
    address    varchar(255),
    is_deleted boolean,
    name       varchar(255)
);

alter table vendors
    owner to postgres;

create table if not exists drugs
(
    id          bigint generated by default as identity
        primary key,
    description varchar(1024),
    image       varchar(255),
    is_deleted  boolean,
    name        varchar(255),
    price       numeric(18, 2),
    quantity    integer,
    storage_id  bigint not null
        constraint fklg1us2bt2m350c4c1q8257cte
            references storages,
    vendor_id   bigint not null
        constraint fkn0x80gdkg2btt9f0xefbjuxwr
            references vendors
);

alter table drugs
    owner to postgres;

create table if not exists orders
(
    id         bigint generated by default as identity
        primary key,
    is_deleted boolean,
    quantity   integer,
    drug_id    bigint not null
        constraint fke2pcx5p20t3ck0qptn8pqq23v
            references drugs,
    receipt_id bigint not null
        constraint fkggqhsju4qm5gwik74xgnhquq3
            references receipts
);

alter table orders
    owner to postgres;
