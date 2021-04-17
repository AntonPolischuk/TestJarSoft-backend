INSERT INTO categories(name, req_name, deleted)
VALUES ('Rock', 'rock', 0),
       ('Pop', 'pop', 0),
       ('Jazz', 'jazz', 0),
       ('Metal', 'metal', 0);

INSERT INTO banners(name, content, deleted, price, category_id)
VALUES (' Like a Rolling Stone',
        'Once upon a time you dressed so fine Threw the bums a dime in your prime, didn’t you?',
        0, 11.1, 1),
       ('Imagine',
        'Imagine there s no heaven, It''''s easy if you try, ',
        0, 20.20, 1),
       ('(I Can’t Get No) Satisfaction',
        'I can''''t get no satisfaction I can''''t get no satisfaction',
        0, 12.99, 1),
       (' Englishman in New York',
        'I don''t drink coffee I take tea my dear I like my toast done on the side ',
        0, 7.99, 3),
       ('What a Wonderful World',
        'I see trees of green, red roses too I see them bloom for me and you ... ',
        0, 5.99, 3),
       ('Moon River',
        'Moon river, wider than a mile I''''m crossin'''' you in style some day ... ',
        0, 5.50, 3),
       ('Billie Jean',
        'She was more like a beauty queen from a movie scene
  I said don''t mind, but what do you mean, I am the one ... ',
        0, 1.99, 2),
       ('Bahama Mama ',
        'Bahama, bahama mama
  Got the biggest house in town bahama mama ... ',
        0, 10.10, 2);