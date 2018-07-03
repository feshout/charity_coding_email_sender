-- we don't know how to generate database mailservice (class Database) :(

create table template
(
	id bigserial not null
		constraint template_pkey
			primary key,
	description varchar(255),
	header varchar(255),
	title varchar(255),
	constraint ukg9roiadj2l3xhsnsk5eoyjd3c
		unique (id, header, title, description),
	update_date TIMESTAMP
)
;

create table send
(
	id bigserial not null
		constraint send_pkey
			primary key,
	receiver varchar(255),
	template_id bigint
		constraint fkn1wxu83cht3j9hvjbg0kdo59p
			references template
)
;

