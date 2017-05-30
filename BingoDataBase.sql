-- MySQL Workbench Synchronization
-- Generated: 2017-05-23 22:27
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Ultron

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER SCHEMA `bingo`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `bingo`.`User` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `Nome` VARCHAR(115) NULL DEFAULT NULL,
  `Email` VARCHAR(75) NULL DEFAULT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `bingo`.`Jogo` (
  `ID` INT(11) NOT NULL,
  `DataTermino` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `bingo`.`Jogos_has_Ganhadores` (
  `User_ID` INT(10) UNSIGNED NOT NULL,
  `Jogo_ID` INT(11) NOT NULL,
  PRIMARY KEY (`User_ID`, `Jogo_ID`),
  INDEX `fk_User_has_jogo_jogo1_idx` (`Jogo_ID` ASC),
  INDEX `fk_User_has_jogo_User_idx` (`User_ID` ASC),
  CONSTRAINT `fk_User_has_jogo_User`
    FOREIGN KEY (`User_ID`)
    REFERENCES `bingo`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_jogo_jogo1`
    FOREIGN KEY (`Jogo_ID`)
    REFERENCES `bingo`.`Jogo` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DELIMITER $$

USE `bingo`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`Jogo_BEFORE_INSERT` BEFORE INSERT ON `Jogo` FOR EACH ROW
BEGIN
	set @ultimaDataAtual := (SELECT Jogo.DataTermino 
							FROM Jogos_has_Ganhadores 
                            INNER JOIN Jogos ON Jogo.ID = Jogos_has_Ganhadores.Jogo_ID;
							WHERE YEAR(Jogo.DataTermino) = YEAR(now()) 
							AND MONTH(Jogo.DataTermino) =  MONTH(now()));
	IF (ISNULL(@ultimaDataAtual)) THEN
		DELETE FROM Jogos_has_Ganhadores;
    END IF;
	
END$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
