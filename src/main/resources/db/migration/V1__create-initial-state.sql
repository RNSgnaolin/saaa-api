create table persons(
    id bigint not null auto_increment,
    name varchar(50) not null,
    representative varchar(50),
    phone bigint not null,

    primary key(id),
    unique(name)
);

create table aircrafts(
    id bigint not null auto_increment,
    brand varchar(50) not null,
    model varchar(50) not null,
    tail_number varchar(10) not null,
    callsign varchar(20) not null,
    pending tinyint not null,
    active tinyint not null,
    type varchar(50) not null,
    person_id bigint not null,
    
    primary key(id),
    unique(tail_number),
    constraint fk_aircrafts_person_id foreign key(person_id) references persons(id)
);

create table users(
    id bigint not null auto_increment,
    login varchar(50) not null,
    password varchar(50) not null,
    saaa tinyint not null,
    admin tinyint not null,

    primary key(id)
);

create table relations(
    user_id bigint not null,
    person_id bigint not null,

    primary key(user_id, person_id),
    constraint fk_relations_user_id foreign key(user_id) references users(id),
    constraint fk_relations_person_id foreign key(person_id) references persons(id)
)

