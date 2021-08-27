<<<<<<< HEAD
insert into albums
values (1,'Seliath', 'Endio', 1);

insert into artists
values (1, 'Davey Alg');

insert into users
values ('John Gill', 'Gilly', 'johngill');
=======
INSERT INTO `artists` (`name`) VALUES ('Kirk Hammett');
INSERT INTO `genres` (`name`, `description`) VALUES ('Rock', 'Rock genre');
INSERT INTO `albums` (`cover`, `name`, `artist_id`) VALUES ('image', 'To live is to die', 1);
insert into `users` (`full_name`, `user_name`, `password`) VALUES ('Dave', 'davealg', 'davidalgie');
INSERT INTO `playlists` (`name`, `artwork`, `description`, `user_id`) VALUES ('Good vibes', 'image', 'My chill playlist', 1);
INSERT INTO `tracks` (`name`, `duration`, `lyrics`, `album_id`, `genre_id`) VALUES ('Matters', 200, 'it matters', 1, 1);
INSERT INTO `playlist_tracks` (`id`, `playlist_id`, `track_id`) VALUES (1, 1, 1);
>>>>>>> ee63bddcfcce0906fba3ee882a6ff81ff5cdf3a2
