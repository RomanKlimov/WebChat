INSERT INTO public.users ( hash_password, last_name, login, name, role) VALUES ( '$2a$10$C8/aE4bbuA.qqJ76nMEyi.islpN8mJ8kvyaWgwCal/Z0jOiRgspW2', 'klimov', 'roman', 'roman', 'USER');
INSERT INTO public.users ( hash_password, last_name, login, name, role) VALUES ( '$2a$10$KTaaEkQNVcN6UQDGnnEwYe3tPEUSWjKkwZ9BDjfvTu3gKfyX5zj56', 'kuznecov', 'max', 'max', 'USER');
INSERT INTO public.users ( hash_password, last_name, login, name, role) VALUES ( '$2a$10$EKhee2gFFk3XKo6MWwprqOzRCgAvHxMyVL8JSl4HCKU76fwSK7qi6', 'kiku', 'misha', 'misha', 'USER');
INSERT INTO public.users ( hash_password, last_name, login, name, role) VALUES ( '$2a$10$eyuMRFgmECDHTbjDMgeFNeHrft/2HNvwfE1HPdCodXbdwmyPhLkUW', 'sviridov', 'oleg', 'oleg', 'USER');
INSERT INTO public.users ( hash_password, last_name, login, name, role) VALUES ( '$2a$10$WpJEsp74fQtchSWDYELLLOpOte19XKBP5JLNvmTudwSreD5pcW5qS', 'bot', 'bot', 'bot', 'USER');

INSERT INTO public.dialogs (title) VALUES ('1');
INSERT INTO public.dialogs (title) VALUES ('2');
INSERT INTO public.dialogs (title) VALUES ('3');
INSERT INTO public.dialogs (title) VALUES ('4');

INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (1, 1);
INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (1, 2);
INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (2, 1);
INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (2, 3);
INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (3, 1);
INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (3, 4);
INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (4, 1);
INSERT INTO public.user_dialog (dialog_id, user_id) VALUES (4, 5);

INSERT INTO public.messages ( creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'Hey!', 1, 1);
INSERT INTO public.messages ( creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'Hii', 1, 2);
INSERT INTO public.messages (creation_date, value, dialog_id, user_id) VALUES ('2018.07.08', 'How it''s going??', 1, 1);
INSERT INTO public.messages (creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'good!', 1, 2);
INSERT INTO public.messages ( creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'Hello Misha!', 2, 1);
INSERT INTO public.messages ( creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'heeeey', 2, 3);
INSERT INTO public.messages ( creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'Oleeeg', 3, 1);
INSERT INTO public.messages ( creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'Where r u??', 3, 1);
INSERT INTO public.messages ( creation_date, value, dialog_id, user_id) VALUES ( '2018.07.08', 'hmm', 3, 4);
