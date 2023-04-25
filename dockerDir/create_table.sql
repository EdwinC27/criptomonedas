CREATE TABLE informacion
(
    endpoint character varying,
    hora character varying,
    status character varying
);

CREATE TABLE users (
    users character varying,
    password character varying,
    token character varying
);

INSERT INTO public.users(
	users, password, token)
	VALUES ('edwin', '20254820b2c8ea8a3ff8705e46509a2bf4fd9a6b8243e64b7dabe118f8ff7639', '');