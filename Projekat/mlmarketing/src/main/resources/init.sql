-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tim8
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tim8` ;

-- -----------------------------------------------------
-- Schema tim8
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tim8` DEFAULT CHARACTER SET utf8 ;
USE `tim8` ;

-- -----------------------------------------------------
-- Table `tim8`.`korisnik`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`korisnik` ;

CREATE TABLE IF NOT EXISTS `tim8`.`korisnik` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(60) NOT NULL,
  `Telefon` VARCHAR(45) NOT NULL,
  `Tip` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim8`.`proizvod`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`proizvod` ;

CREATE TABLE IF NOT EXISTS `tim8`.`proizvod` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(90) NOT NULL,
  `nabavnacijena` DOUBLE NOT NULL,
  `prodajnacijena` DOUBLE NOT NULL,
  `kolicina` INT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `naziv_UNIQUE` (`naziv` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim8`.`regija`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`regija` ;

CREATE TABLE IF NOT EXISTS `tim8`.`regija` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(90) NOT NULL,
  `drzava` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ime_UNIQUE` (`ime` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim8`.`akterProdaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`akterProdaje` ;

CREATE TABLE IF NOT EXISTS `tim8`.`akterProdaje` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `tip` VARCHAR(45) NOT NULL,
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(45) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  `brojtelefona` VARCHAR(45) NOT NULL,
  `regijaid` INT NOT NULL,
  `odgovornimenadzer` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_akterProdaje_regija1_idx` (`regijaid` ASC),
  INDEX `fk_akterProdaje_akterProdaje1_idx` (`odgovornimenadzer` ASC),
  CONSTRAINT `fk_akterProdaje_regija1`
    FOREIGN KEY (`regijaid`)
    REFERENCES `tim8`.`regija` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_akterProdaje_akterProdaje1`
    FOREIGN KEY (`odgovornimenadzer`)
    REFERENCES `tim8`.`akterProdaje` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim8`.`narudzba`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`narudzba` ;

CREATE TABLE IF NOT EXISTS `tim8`.`narudzba` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `datum` DATETIME NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `akterid` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_narudzba_akterProdaje1_idx` (`akterid` ASC),
  CONSTRAINT `fk_narudzba_akterProdaje1`
    FOREIGN KEY (`akterid`)
    REFERENCES `tim8`.`akterProdaje` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim8`.`proizvod_narudzba`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`proizvod_narudzba` ;

CREATE TABLE IF NOT EXISTS `tim8`.`proizvod_narudzba` (
  `proizvodid` INT NOT NULL,
  `narudzbaid` INT NOT NULL,
  `kolicina` INT NOT NULL,
  `ID` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  INDEX `fk_proizvod_narudzba_proizvod_idx` (`proizvodid` ASC),
  INDEX `fk_proizvod_narudzba_narudzba1_idx` (`narudzbaid` ASC),
  CONSTRAINT `fk_proizvod_narudzba_proizvod`
    FOREIGN KEY (`proizvodid`)
    REFERENCES `tim8`.`proizvod` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proizvod_narudzba_narudzba1`
    FOREIGN KEY (`narudzbaid`)
    REFERENCES `tim8`.`narudzba` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim8`.`faktura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`faktura` ;

CREATE TABLE IF NOT EXISTS `tim8`.`faktura` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ukupnacijena` DOUBLE NOT NULL,
  `datum` DATETIME NOT NULL,
  `akterid` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_faktura_akterProdaje1_idx` (`akterid` ASC),
  CONSTRAINT `fk_faktura_akterProdaje1`
    FOREIGN KEY (`akterid`)
    REFERENCES `tim8`.`akterProdaje` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tim8`.`proizvod_faktura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tim8`.`proizvod_faktura` ;

CREATE TABLE IF NOT EXISTS `tim8`.`proizvod_faktura` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `proizvodid` INT NOT NULL,
  `fakturaid` INT NOT NULL,
  `kolicina` INT NOT NULL,
  `cijena` DOUBLE NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_proizvod_faktura_proizvod1_idx` (`proizvodid` ASC),
  INDEX `fk_proizvod_faktura_faktura1_idx` (`fakturaid` ASC),
  CONSTRAINT `fk_proizvod_faktura_proizvod1`
    FOREIGN KEY (`proizvodid`)
    REFERENCES `tim8`.`proizvod` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proizvod_faktura_faktura1`
    FOREIGN KEY (`fakturaid`)
    REFERENCES `tim8`.`faktura` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
