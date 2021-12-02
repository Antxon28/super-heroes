BEGIN;

DROP TABLE superhero;
CREATE TABLE superhero (
    id int,
    name varchar(255),
    ability varchar(255)
);

INSERT INTO `superhero` (`id`, `name`, `ability`) VALUES
    (1, 'Batman', 'Jump'),
    (2, 'Superman', 'Strong'),
    (3, 'Spiderman', 'Webs')
;

COMMIT;