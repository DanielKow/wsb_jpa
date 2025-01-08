-- Insert Data into Address Table
INSERT INTO ADDRESS (id, address_line1, address_line2, city, postal_code)
VALUES
    (1, 'ul. Szkolna 21/12', NULL, 'Prudnik', '48-200'),
    (2, 'ul. Dworcowa 25', 'lok. 23', 'Gliwice', '44-100'),
    (3, 'ul. Mickiewicza 22', NULL, 'Kraków', '30-001'),
    (4, 'ul. Wojska Polskiego 28/3', NULL, 'Warszawa', '00-001'),
    (5, 'ul. Lipowa 29/4', NULL, 'Gdańsk', '80-001'),
    (6, 'ul. Leśna 20A', NULL, 'Katowice', '40-200'),
    (7, 'ul. Krótka 212', 'lok. 45', 'Poznań', '60-101'),
    (8, 'ul. Długa 23', NULL, 'Wrocław', '50-200'),
    (9, 'ul. Chopina 24/6', NULL, 'Sopot', '81-701'),
    (10, 'ul. Wrzosowa 25', NULL, 'Łódź', '90-100'),
    (11, 'ul. Opolska 36', NULL, 'Opole', '45-011'),
    (12, 'ul. Przyjaźni 37', 'lok. 89', 'Bydgoszcz', '85-019'),
    (13, 'ul. Nadrzeczna 38', NULL, 'Lublin', '20-001'),
    (14, 'ul. Główna 39', 'lok. 134', 'Rzeszów', '35-001'),
    (15, 'ul. Szewska 40', NULL, 'Białystok', '15-011'),
    (16, 'ul. Kwiatowa 41', 'lok. 12B', 'Zielona Góra', '65-200'),
    (17, 'ul. Zamkowa 42', 'lok. 45C', 'Toruń', '87-100'),
    (18, 'ul. Śląska 43', NULL, 'Częstochowa', '42-200'),
    (19, 'ul. Racławicka 44', NULL, 'Racibórz', '47-400'),
    (20, 'ul. Wodna 45', NULL, 'Włocławek', '87-800'),
    (21, 'ul. Tęczowa 22', 'lok. 23', 'Gliwice', '44-100'),
    (22, 'ul. Wiosenna 23', NULL, 'Kraków', '30-001'),
    (23, 'ul. Jesienna 24', NULL, 'Warszawa', '00-001'),
    (24, 'ul. Letnia 25', NULL, 'Gdańsk', '80-001'),
    (25, 'ul. Zimowa 20A', NULL, 'Katowice', '40-200'),
    (26, 'ul. Letnia 212', 'lok. 45', 'Poznań', '60-101'),
    (27, 'ul. Zimowa 23', NULL, 'Wrocław', '50-200'),
    (28, 'ul. Letnia 24/6', NULL, 'Sopot', '81-701'),
    (29, 'ul. Zimowa 25', NULL, 'Łódź', '90-100'),
    (30, 'ul. Letnia 36', NULL, 'Opole', '45-011'),
    (31, 'ul. Zimowa 37', 'lok. 89', 'Bydgoszcz', '85-019'),
    (32, 'ul. Letnia 38', NULL, 'Lublin', '20-001'),
    (33, 'ul. Zimowa 39', 'lok. 134', 'Rzeszów', '35-001'),
    (34, 'ul. Letnia 40', NULL, 'Białystok', '15-011'),
    (35, 'ul. Zimowa 41', 'lok. 12B', 'Zielona Góra', '65-200'),
    (36, 'ul. Letnia 42', 'lok. 45C', 'Toruń', '87-100'),
    (37, 'ul. Zimowa 43', NULL, 'Częstochowa', '42-200'),
    (38, 'ul. Letnia 44', NULL, 'Racibórz', '47-400'),
    (39, 'ul. Zimowa 45', NULL, 'Włocławek', '87-800');

-- Insert Data into Patient Table
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, number_of_cheesecakes, sex, version)
VALUES
    (1, 'Jan', 'Kowalski', '+48500123456', 'jan.kowalski@example.com', 'P1234', '1990-01-01', 1, 1, 'MALE', 0),
    (2, 'Anna', 'Nowak', '+48500987654', 'anna.nowak@example.com', 'P5678', '1992-02-02', 2, 22, 'FEMALE', 0),
    (3, 'Piotr', 'Wiśniewski', '+48500321456', 'piotr.wisniewski@example.com', 'P9101', '1985-03-03', 3, 13, 'MALE', 0),
    (4, 'Maria', 'Wójcik', '+48500654321', 'maria.wojcik@example.com', 'P1122', '1991-04-04', 4, 30, 'FEMALE', 0),
    (5, 'Krzysztof', 'Kowalczyk', '+48500789012', 'krzysztof.kowalczyk@example.com', 'P3344', '1983-05-05', 5, 65, 'MALE', 0),
    (6, 'Agnieszka', 'Zielińska', '+48500876543', 'agnieszka.zielinska@example.com', 'P5566', '1977-06-06', 6, 11, 'FEMALE', 0),
    (7, 'Tomasz', 'Szymański', '+48500678345', 'tomasz.szymanski@example.com', 'P7788', '1954-07-07', 7, 22, 'MALE', 0),
    (8, 'Katarzyna', 'Lewandowska', '+48500765432', 'katarzyna.lewandowska@example.com', 'P9900', '2002-08-08', 8, 50, 'FEMALE', 0),
    (9, 'Marek', 'Dąbrowski', '+48500122334', 'marek.dabrowski@example.com', 'P0011', '1989-09-09', 9, 100, 'MALE', 0),
    (10, 'Magdalena', 'Zając', '+48500455667', 'magdalena.zajac@example.com', 'P2233', '1989-11-11', 10, 349, 'FEMALE', 0),
    (11, 'Zbychu', 'Kowalski', '+48500123456', 'zbychujestsuper@wp.pl', 'P4444', '1973-04-01', 21, 783, 'MALE', 0),
    (12, 'Zuzanna', 'Nowak', '+48500987654', 'zuzialaklanieduza@onet.pl', 'P5555', '2011-02-02', 22, 15, 'FEMALE', 0),
    (13, 'Gracjan', 'Kowalski', '+48500321456', 'gk@m.com', 'P6666', '1995-03-03', 23, 1, 'MALE', 0);

-- Insert Data into Doctor Table
INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
    (1, 'Alicja', 'Brązowa', '+48500987654', 'alicja.brazowa@example.com', 'D7890', 'SURGEON', 11),
    (2, 'Bartosz', 'Biały', '+48500654321', 'bartosz.bialy@example.com', 'D4567', 'GP', 12),
    (3, 'Karol', 'Zielony', '+48500765432', 'karol.zielony@example.com', 'D5678', 'DERMATOLOGIST', 13),
    (4, 'Dawid', 'Niebieski', '+48500789012', 'dawid.niebieski@example.com', 'D6789', 'OCULIST', 14),
    (5, 'Emilia', 'Różowa', '+48500876543', 'emilia.rozowa@example.com', 'D7891', 'SURGEON', 15),
    (6, 'Franciszek', 'Czarny', '+48500987654', 'franciszek.czarny@example.com', 'D8902', 'GP', 16),
    (7, 'Grażyna', 'Biała', '+48500543210', 'grazyna.biala@example.com', 'D9013', 'DERMATOLOGIST', 17),
    (8, 'Henryk', 'Piątek', '+48500786543', 'henryk.piatek@example.com', 'D1123', 'OCULIST', 18),
    (9, 'Iwona', 'Zielona', '+48500456789', 'iwona.zielona@example.com', 'D3344', 'SURGEON', 19),
    (10, 'Jacek', 'Czarny', '+48500234567', 'jacek.czarny@example.com', 'D5566', 'GP', 20);

-- Insert Data into Medical Treatment Table
INSERT INTO MEDICAL_TREATMENT (id, description, type)
VALUES
    (1, 'Ultrasonografia jamy brzusznej', 'USG'),
    (2, 'EKG wysiłkowe', 'EKG'),
    (3, 'RTG klatki piersiowej', 'RTG'),
    (4, 'Ultrasonografia serca', 'USG'),
    (5, 'EKG - badanie spoczynkowe', 'EKG'),
    (6, 'RTG kości', 'RTG'),
    (7, 'USG narządów rodnych', 'USG'),
    (8, 'EKG długoterminowe', 'EKG'),
    (9, 'RTG płuc', 'RTG'),
    (10, 'USG tarczycy', 'USG'),
    (11, 'Badanie RTG', 'RTG'),
    (12, 'Badanie EKG', 'EKG'),
    (13, 'Badanie RTG', 'RTG');

-- Insert Data into Visit Table
INSERT INTO VISIT (id, description, time, doctor_id, patient_id)
VALUES
    (1, 'Badanie USG', '2023-10-10 10:00:00', 1, 1),
    (2, 'Konsultacja z EKG', '2023-10-11 11:00:00', 2, 2),
    (3, 'Badanie RTG', '2023-10-12 12:00:00', 3, 3),
    (4, 'Ultrasonografia kontrolna', '2023-10-13 09:00:00', 4, 4),
    (5, 'Ocena EKG', '2023-10-14 08:00:00', 5, 5),
    (6, 'RTG kręgosłupa', '2023-10-15 10:30:00', 6, 6),
    (7, 'Szczepienie i EKG', '2023-10-16 14:00:00', 7, 7),
    (8, 'Kontrola pooperacyjna USG', '2023-10-17 16:00:00', 8, 8),
    (9, 'Przegląd wyników RTG', '2023-10-18 15:00:00', 9, 9),
    (10, 'Sesja końcowa USG', '2023-10-19 13:30:00', 10, 10),
    (11, 'Badanie RTG', '2023-10-20 10:00:00', 2, 1),
    (12, 'RTG i EKG', '2023-10-21 11:00:00', 9, 1),
    (13, 'Badanie USG', '2023-10-22 12:00:00', 10, 2),
    (15, 'Badanie RTG', '2023-10-24 08:00:00', 2, 3),
    (16, 'Ultrasonografia kontrolna', '2023-10-25 10:30:00', 3, 3),
    (17, 'Ocena EKG', '2023-10-26 14:00:00', 4, 4),
    (18, 'RTG kręgosłupa', '2023-10-27 16:00:00', 5, 5),
    (19, 'Szczepienie i EKG', '2023-10-28 15:00:00', 6, 6),
    (20, 'Kontrola pooperacyjna USG', '2023-10-29 13:30:00', 7, 7),
    (21, 'Przegląd wyników RTG', '2023-10-30 10:00:00', 8, 8),
    (22, 'Sesja końcowa USG', '2023-10-31 11:00:00', 9, 9);

-- Insert Data into VISITS_TO_TREATMENTS Table (for Many-to-Many Relationship)
INSERT INTO VISITS_TO_TREATMENTS (visit_id, treatment_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (12, 13);