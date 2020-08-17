--[00:26:39.664] npc_dota_hero_bane is killed by npc_dota_creep_goodguys_melee
insert into dota_match(id) values (1597653254777);
insert into dota_hero(id, name) values ('5c4eff74-2bef-49cf-9f7c-1831fe0a99ba', 'npc_dota_creep_goodguys_melee');
insert into dota_hero(id, name) values ('796734b6-7f36-45c0-ab51-ff32dd5d8dab', 'npc_dota_hero_bane');

INSERT INTO dota_events(
	id, event_type, timestamp, match, source)
	VALUES ('bdd65b66-4d28-4172-b738-39fdf1922995',
	'KILL', 1599664, 1597653254777, '5c4eff74-2bef-49cf-9f7c-1831fe0a99ba');

INSERT INTO dota_events_kill(event, target)
	VALUES ('bdd65b66-4d28-4172-b738-39fdf1922995', '796734b6-7f36-45c0-ab51-ff32dd5d8dab');
