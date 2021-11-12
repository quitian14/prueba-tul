INSERT INTO public.permissions (id,"name") VALUES(1,'create');
INSERT INTO public.permissions (id,"name") VALUES(2,'delete');
INSERT INTO public.permissions (id,"name") VALUES(3,'update');
INSERT INTO public.permissions (id,"name") VALUES(4,'get');

INSERT INTO public.users (user_name, "password", mail, "name") VALUES('jose1234', '827CCB0EEA8A706C4C34A16891F84E7B', 'jose@mail.com', 'jose');
INSERT INTO public.users (user_name, "password", mail, "name") VALUES('maria1234', '827CCB0EEA8A706C4C34A16891F84E7B', 'maria@mail.com', 'maria');
INSERT INTO public.users (user_name, "password", mail, "name") VALUES('pepe1234', '827CCB0EEA8A706C4C34A16891F84E7B', 'pepe@mail.com', 'pepe');

INSERT INTO public.user_permissions (user_name, permission_id) VALUES('jose1234', 1);
INSERT INTO public.user_permissions (user_name, permission_id) VALUES('jose1234', 2);
INSERT INTO public.user_permissions (user_name, permission_id) VALUES('jose1234', 3);
INSERT INTO public.user_permissions (user_name, permission_id) VALUES('jose1234', 4);

INSERT INTO public.user_permissions (user_name, permission_id) VALUES('maria1234', 2);
INSERT INTO public.user_permissions (user_name, permission_id) VALUES('maria1234', 3);

INSERT INTO public.user_permissions (user_name, permission_id) VALUES('pepe1234', 1);
INSERT INTO public.user_permissions (user_name, permission_id) VALUES('pepe1234', 3);

INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130003', 'cemento', '12341234', 'construccion', 25000, 'SIMPLE', now(), now());
INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130004', 'puntillas', '12341234', 'construccion', 2000, 'SIMPLE', now(), now());
INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130005', 'madera', '12341234', 'construccion', 35000, 'SIMPLE', now(), now());
INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130006', 'puertas', '12341234', 'construccion', 45000, 'SIMPLE', now(), now());
INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130007', 'baldosas', '12341234', 'construccion', 200, 'SIMPLE', now(), now());
INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130008', 'luces', '12341234', 'construccion', 250, 'SIMPLE', now(), now());
INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130009', 'sanitario', '12341234', 'construccion', 3500, 'SIMPLE', now(), now());
INSERT INTO public.product (id, name, sku, description, price, "type", created_at, updated_at) VALUES('5fabbb92-4370-11ec-81d3-0242ac130010', 'platon', '12341234', 'construccion', 45000, 'DISCOUNT', now(), now());

