CREATE TYPE continent AS ENUM ('ASIA', 'EUROPE', 'NORTH_AMERICA', 'SOUTH_AMERICA', 'AFRICA', 'AUSTRALIA', 'ANTARCTICA'); --Creating ENUM Type for Enum class continent

CREATE TYPE is_official AS ENUM('T', 'F') --Creating ENUM Type for Enum class is_official

--Creating a table country 
CREATE TABLE country (
    code VARCHAR(3) PRIMARY KEY,
    cname VARCHAR(52) NOT NULL,
    continent_type continent NOT NULL,
    region VARCHAR(26),
    surface_area DECIMAL(10, 2),
    indep_year SMALLINT,
    population INT,
    life_expectancy DECIMAL(3, 1),
    gnp DECIMAL(10, 2),
    gnp_old DECIMAL(10, 2),
    local_name VARCHAR(45),
    government_form VARCHAR(45),
    head_of_state VARCHAR(60),
    capital INT,
    code2 VARCHAR(2)
);
 
---Creating a table  City
CREATE TABLE city (id SERIAL PRIMARY KEY,  
				   name VARCHAR(255) NOT NULL,  
				   country_code VARCHAR(3) NOT NULL REFERENCES country(Code), 
				   district VARCHAR(255), 
				   population INT NOT NULL );
				   

--Creating a table country_language

CREATE TABLE country_language(
    country_code VARCHAR(3) REFERENCES country(Code),
    language 	VARCHAR(30),
    is_official_type is_official,
    percentage DECIMAL(4,1),
    PRIMARY KEY (language)
);	

--------------------------------------------------------------------------
--Insert Query for Table Country:
-- Example 1
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('USA', 'United States', 'NORTH_AMERICA', 'North America', 9833517.85, 1776, 331002651, 78.8, 21433225.0, 20510222.0, 'United States', 'Federal Republic', 'Joe Biden', 1, 'US');
 
-- Example 2
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('CHN', 'China', 'ASIA', 'Eastern Asia', 9596960.00, -2070, 1439323776, 76.9, 1434292.0, 1211357.0, 'Zhōnghuá Rénmín Gònghéguó', 'People''s Republic', 'Xi Jinping', 1891, 'CN');
 
-- Example 3
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('IND', 'India', 'ASIA', 'Southern and Central Asia', 3287263.00, 1947, 1380004385, 69.7, 2875140.0, 2716680.0, 'Bhārat', 'Federal Republic', 'Ram Nath Kovind', 1101, 'IN');
 
-- Example 4
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('BRA', 'Brazil', 'SOUTH_AMERICA', 'South America', 8547403.00, 1822, 212559417, 75.5, 2139937.0, 1861326.0, 'Brasil', 'Federal Republic', 'Jair Bolsonaro', 2118, 'BR');
 
-- Example 5
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('RUS', 'Russia', 'EUROPE', 'Eastern Europe', 17098242.00, 1991, 145912025, 71.6, 4692000.0, 3663600.0, 'Rossiyskaya Federatsiya', 'Federal Republic', 'Vladimir Putin', 3580, 'RU');
 
-- Example 6
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('NGA', 'Nigeria', 'AFRICA', 'Western Africa', 923768.00, 1960, 206139587, 54.7, 442976.0, 420775.0, 'Nigeria', 'Federal Republic', 'Muhammadu Buhari', 2754, 'NG');
 
-- Example 7
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('JPN', 'Japan', 'ASIA', 'Eastern Asia', 377975.00, -660, 126476461, 84.6, 5179980.0, 4932226.0, 'Nihon/Nippon', 'Constitutional Monarchy', 'Emperor Naruhito', 1532, 'JP');
 
-- Example 8
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('MEX', 'Mexico', 'NORTH_AMERICA', 'Central America', 1964375.00, 1810, 128932753, 75.5, 2267699.0, 2057274.0, 'Estados Unidos Mexicanos', 'Federal Republic', 'Andrés Manuel López Obrador', 5581, 'MX');
 
-- Example 9
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('DEU', 'Germany', 'EUROPE', 'Western Europe', 357022.00, 1871, 83783942, 80.9, 4213667.0, 3763537.0, 'Deutschland', 'Federal Republic', 'Frank-Walter Steinmeier', 3068, 'DE');
 
-- Example 10
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('EGY', 'Egypt', 'AFRICA', 'Northern Africa', 1001450.00, 1952, 104258327, 72.0, 235366.0, 229895.0, 'Mişr', 'Republic', 'Abdel Fattah el-Sisi', 3604, 'EG');
 
-- Example 11
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('IDN', 'Indonesia', 'ASIA', 'Southeast Asia', 1904569.00, 1945, 273523615, 71.7, 1047282.0, 917015.0, 'Indonesia', 'Republic', 'Joko Widodo', 939, 'ID');
 
-- Example 12
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('PAK', 'Pakistan', 'ASIA', 'Southern and Central Asia', 881913.00, 1947, 220892331, 67.3, 1109123.0, 1019468.0, 'Pakistan', 'Islamic Republic', 'Arif Alvi', 3333, 'PK');
 
-- Example 13
 
-- Example 14
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('ARG', 'Argentina', 'SOUTH_AMERICA', 'South America', 2780400.00, 1816, 45195777, 76.3, 547508.0, 484781.0, 'Argentina', 'Federal Republic', 'Alberto Fernández', 69, 'AR');
 
-- Example 15
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('FRA', 'France', 'EUROPE', 'Western Europe', 551695.00, 843, 65273511, 82.3, 2846885.0, 2777321.0, 'France', 'Republic', 'Emmanuel Macron', 2974, 'FR');
 
-- Example 16
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('SAU', 'Saudi Arabia', 'ASIA', 'Middle East', 2149690.00, 1932, 34813871, 75.1, 1859894.0, 1731069.0, 'Al-Mamlakah Al-‘Arabiyyah As-Su‘ūdiyyah', 'Absolute Monarchy', 'Salman bin Abdulaziz Al Saud', 3173, 'SA');
 
-- Example 17
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('AUS', 'Australia', 'AUSTRALIA', 'Australia and New Zealand', 7692024.00, 1901, 25499884, 83.2, 1568820.0, 1537744.0, 'Australia', 'Constitutional Monarchy', 'Elizabeth II', 135, 'AU');
 
-- Example 18
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('TUR', 'Turkey', 'ASIA', 'Middle East', 783356.00, 1923, 84339067, 75.8, 713510.0, 630208.0, 'Türkiye', 'Republic', 'Recep Tayyip Erdoğan', 3358, 'TR');
 
-- Example 19
INSERT INTO Country (code, cname, continent_type, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, head_of_state, capital, code2)
VALUES ('THA', 'Thailand', 'ASIA', 'Southeast Asia', 513120.00, 1238, 69799978, 77.1, 455221.0, 403002.0, 'Prathet Thai', 'Constitutional Monarchy', 'Vajiralongkorn', 3320, 'TH');


------------------------------------------------------------------
--Insert queries for table City:

INSERT INTO City ( name, country_code, district, population)
VALUES ('New York', 'USA', 'New York', 8398748);
-- Example 2
INSERT INTO City ( name, country_code, district, population)
VALUES ( 'Beijing', 'CHN', 'Beijing', 21516000);
-- Example 3
INSERT INTO City (name, country_code, district, population)
VALUES ( 'Mumbai', 'IND', 'Maharashtra', 12442373);
-- Example 4
INSERT INTO City (name, country_code, district, population)
VALUES ( 'Rio de Janeiro', 'BRA', 'Rio de Janeiro', 6747815);
-- Example 5
INSERT INTO City ( name, country_code, district, population)
VALUES ('Moscow', 'RUS', 'Moscow', 12692466);
-- Example 6
INSERT INTO City ( name, country_code, district, population)
VALUES ('Lagos', 'NGA', 'Lagos', 14974218);
-- Example 7
INSERT INTO City ( name, country_code, district, population)
VALUES ('Tokyo', 'JPN', 'Tokyo', 37393129);
-- Example 8
INSERT INTO City ( name, country_code, district, population)
VALUES ( 'Mexico City', 'MEX', 'Mexico City', 8918653);
-- Example 9
INSERT INTO City ( name, country_code, district, population)
VALUES ('Berlin', 'DEU', 'Berlin', 3769495);
-- Example 10
INSERT INTO City ( name, country_code, district, population)
VALUES ('Cairo', 'EGY', 'Cairo', 9278441);

---------------------------------------------------------------

--Insert queries for table country_language:

INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('USA', 'US-English', 'T', 80.0);
 
-- Example 2
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('CHN', 'CHN-Mandarin', 'T', 70.5);
 
-- Example 3
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('IND', 'IND-Hindi', 'T', 41.0);
 
-- Example 4
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('BRA', 'BRA-Portuguese', 'T', 97.3);
 
-- Example 5
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('RUS', 'RUS-Russian', 'T', 100.0);
 
-- Example 6
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('NGA', 'NGA-English', 'T', 53.0);
 
-- Example 7
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('JPN', 'JPN-Japanese', 'T', 99.0);
 
-- Example 8
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('MEX', 'MEX-Spanish', 'T', 98.5);
 
-- Example 9
INSERT INTO country_language (country_code, language, is_official_type, percentage)
VALUES ('DEU', 'DEU-German', 'T',95.0);

