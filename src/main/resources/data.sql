insert INTO users(username,password,enabled)
values('nadeem',
'nadeem',
true);
insert INTO users(username,password,enabled)
values('fahad',
'fahad',
true);
INSERT  INTO authorities(username,authority)
values ('nadeem','ROLE_USER');
INSERT  INTO authorities(username,authority)
values ('fahad','ROLE_ADMIN');