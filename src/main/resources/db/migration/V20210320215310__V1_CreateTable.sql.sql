create table if not exists clients(
                                      id bigserial primary key,
                                      full_name text not null,
                                      date_of_birth timestamp not null,
                                      gender varchar(10) not null,
    job_status varchar(20) not null,
    phone_number text not null
    );

