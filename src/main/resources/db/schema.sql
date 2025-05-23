# USE `schedule-project-advanced`;
#
#
# CREATE TABLE `users` (
#                          `id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
#                          `email`	VARCHAR(20)	NULL,
#                          `password`	VARCHAR(40)	NULL,
#                          `nickname`	VARCHAR(20)	NULL,
#                          `refresh_token`	TEXT	NULL,
#                          `createAt`	DATE	NOT NULL,
#                          `modifyAt`	DATE	NOT NULL
# );
#
# CREATE TABLE `schedules` (
#                              `id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
#                              `users_id`	int	NOT NULL,
#                              `title`	VARCHAR(20)	NOT NULL,
#                              `content`	VARCHAR(200)	NOT NULL,
#                              `createAt`	DATE	NOT NULL,
#                              `modifyAt`	DATE	NOT NULL,
#                              FOREIGN KEY(`users_id`) REFERENCES `users`(`id`)
# );
#
# CREATE TABLE `comments` (
#                             `id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT,
#                             `schedules_id`	int	NOT NULL,
#                             `users_id`	int	NOT NULL,
#                             `content`	VARCHAR(200)	NOT NULL,
#                             `createAt`	DATE	NOT NULL,
#                             `modifyAt`	DATE	NOT NULL,
#                             FOREIGN KEY(`schedules_id`) REFERENCES `schedules`(`id`),
#                             FOREIGN KEY(`users_id`) REFERENCES `users`(`id`)
# );
