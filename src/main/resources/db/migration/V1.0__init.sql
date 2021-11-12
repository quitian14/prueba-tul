CREATE TABLE public.product (
    id varchar(255) NOT NULL primary key,
	name varchar(500) NOT NULL UNIQUE,
	sku varchar(255) NOT NULL,
	description varchar(600),
	price numeric(12,2) not null,
	type varchar(255) NOT NULL,
	created_at  TIMESTAMP DEFAULT now(),
    updated_at  TIMESTAMP
);

CREATE TABLE public.shopping_cart  (
    id varchar(255) NOT NULL primary key,
    state varchar(255) NOT NULL,
    total numeric(12,2) not null,
	created_at  TIMESTAMP DEFAULT now(),
    updated_at  TIMESTAMP
);

CREATE TABLE public.shopping_cart_detail  (
    id varchar(255) NOT NULL primary key,
    shopping_cart_id varchar(255),
    product jsonb NOT NULL,
    amount integer not null,
	created_at  TIMESTAMP DEFAULT now(),
    updated_at  TIMESTAMP,
    CONSTRAINT shopping_cart_shopping_cart_detail FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id)
);

CREATE TABLE public.users (
	user_name varchar(255) NOT NULL primary key,
	password varchar(255) NOT NULL,
	mail varchar(255) not null UNIQUE,
	name varchar(255) not null
);

CREATE TABLE public.permissions (
    id int8 NOT NULL primary key,
	name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE public.user_permissions (
    id bigserial NOT NULL primary key,
	"user_name" varchar(255) NOT NULL,
	"permission_id" int8 NOT NULL,
	CONSTRAINT user_permission_user FOREIGN KEY (user_name) REFERENCES users(user_name),
	CONSTRAINT user_permission_permission FOREIGN KEY (permission_id) REFERENCES permissions(id)
);
