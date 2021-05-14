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
    constraint fk_id_client_in_invest_account_table foreign key(client_id) references client(id)
    );

create table if not exists share(
    id bigserial primary key,
    country varchar(30) not null,
    name_of_company varchar(50) not null,
    capitalization integer not null,
    stock varchar(10) not null
    );

create table if not exists investment_account_share(
    id_invest_account bigserial not null,
    id_share bigserial not null,
    constraint pk_investment_account_share primary key(id_invest_account, id_share),
    constraint fk_id_invest_account_in_investment_account_share_table foreign key(id_invest_account) references investment_account(id),
    constraint fk_id_share_in_investment_account_share_table foreign key(id_share) references share(id)

    );

create table if not exists deposit(
    id bigserial primary key,
    client_id bigserial not null ,
    date_of_opening date not null,
    date_of_ending date not null,
    money_sum double precision not null,
    percent_per_year double precision not null check(percent_per_year <= 10 and percent_per_year >= 0.1),
    is_replenish integer not null check(is_replenish >= 0 and is_replenish <= 1),
    is_withdraw integer not null check(is_withdraw >= 0 and is_withdraw <= 1),
    constraint fk_id_client_in_invest_account_table foreign key(client_id) references client(id)
    );