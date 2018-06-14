create table send
(
	id bigserial not null
		constraint send_pkey
			primary key,
	receiver varchar(255),
	sender_id bigint,
	template_id bigint
)
;

create table sender
(
	id bigserial not null
		constraint sender_pkey
			primary key,
	host_name varchar(255),
	password varchar(255),
	constraint uki7pm832ldhqnoa93qnllksqli
		unique (host_name, password, id)
)
;

alter table send
	add constraint fkmgbp8xujhoofbhwdopm8o4xl6
		foreign key (sender_id) references sender
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

alter table send
	add constraint fkn1wxu83cht3j9hvjbg0kdo59p
		foreign key (template_id) references template
;


