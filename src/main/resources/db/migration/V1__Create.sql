create table if not exists client(
    id bigserial primary key,
    full_name text not null,
    date_of_birth date not null,
    gender varchar(10) not null,
    job_status varchar(20) not null,
    phone_number text not null
    );

create table if not exists investment_account(
    id bigserial primary key,
    client_id bigserial not null ,
    date_of_opening date not null,
    money_sum double precision not null,
    constraint fk_id_client_in_invest_account_table foreign key (client_id) references client (id)
    );

