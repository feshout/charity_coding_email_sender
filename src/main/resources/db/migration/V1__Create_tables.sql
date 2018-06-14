-- we don't know how to generate database mailservice (class Database) :(
create table sender
(
	id bigserial not null
		constraint sender_pkey
			primary key,
	host_name varchar(255),
	password varchar(255),
	salt varchar(255),
	constraint ukbfn6anbgifih79hb3ir5indpm
		unique (host_name, id)
)
;

create table template
(
	id bigserial not null
		constraint template_pkey
			primary key,
	description varchar(255),
	header varchar(255),
	title varchar(255),
	constraint ukg9roiadj2l3xhsnsk5eoyjd3c
		unique (id, header, title, description)
)
;

create table send
(
	id bigserial not null
		constraint send_pkey
			primary key,
	receiver varchar(255),
	sender_id bigint
		constraint fkmgbp8xujhoofbhwdopm8o4xl6
			references sender,
	template_id bigint
		constraint fkn1wxu83cht3j9hvjbg0kdo59p
			references template
)
;

